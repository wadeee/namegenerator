package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSON;
import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.model.*;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import com.chenhongliang.namegenerator.service.OrderService;
import com.chenhongliang.namegenerator.util.DateUtils;
import com.chenhongliang.namegenerator.util.FortuneTellingUtils;
import com.chenhongliang.namegenerator.util.HttpUtils;
import com.chenhongliang.namegenerator.util.Lunar;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Override
    public Boolean addOrder(OrderForm orderForm) {
        FortuneTellingUtils.getXiYongShen(orderForm);

        OrderModel orderModel = new OrderModel();
        orderModel.setOrderNumber(orderForm.getOrderNumber());
        orderModel.setSalesman(orderForm.getSalesman());
        orderModel.setWechatMachine(orderForm.getWechatMachine());
        orderModel.setNameGiver(orderForm.getNameGiver());
        orderModel.setBills(orderForm.getBills());
        orderModel.setPlan(orderForm.getPlan());
        orderModel.setLastname(orderForm.getLastname());
        orderModel.setSex(orderForm.getSex());
        orderModel.setNameSize(orderForm.getNameSize());
        orderModel.setBirthday(orderForm.getBirthday());
        orderModel.setBirthdayHour(orderForm.getBirthdayHour());
        orderModel.setBirthdayMinute(orderForm.getBirthdayMinute());
        orderModel.setBannedPinyin(orderForm.getBannedPinyin());
        orderModel.setBannedCharacter(orderForm.getBannedCharacter());
        orderModel.setGeneration(orderForm.getGeneration());
        orderModel.setStyle(orderForm.getStyle());
        orderModel.setNotes(orderForm.getNotes());
        orderModel.setStatus("待交付");
        orderModel.setUpdateTime(DateUtils.dateToString(new Date()));
        orderModel.setDelivered(false);
        if (orderModel.getPlan().startsWith("八字")) {
            orderModel.setWuxing(String.join(" ", FortuneTellingUtils.getXiYongShen(orderForm)));
        }

        Date dateNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        cal.add(Calendar.HOUR, orderForm.getTillDeliveryTime());
        orderModel.setDeliveryTime(cal.getTime());
        orderMapper.insert(orderModel);
        Integer orderId = orderModel.getId();

        NameConstrainForm nameConstrainForm = new NameConstrainForm();
        nameConstrainForm.setLastname(orderForm.getLastname());
        nameConstrainForm.setSex(orderForm.getSex());
        List<Integer> nameSizeList = new ArrayList<>();
        if (orderForm.getNameSize().contains("二字名")) {
            nameSizeList.add(1);
        }
        if (orderForm.getNameSize().contains("三字名")) {
            nameSizeList.add(2);
        }
        if (orderForm.getNameSize().contains("四字名")) {
            nameSizeList.add(3);
        }
        nameConstrainForm.setGeneration(orderForm.getGeneration());
        if (!Objects.isNull(orderModel.getWuxing())) {
            nameConstrainForm.setWuxing(Arrays.asList(orderModel.getWuxing().split(" ")));
        }
        nameConstrainForm.setBannedCharacter(splitString(orderForm.getBannedCharacter()));
        nameConstrainForm.setBannedPinyin(splitString(orderForm.getBannedPinyin()));
        List<OrderGeneratedNameModel> generatedCharacterNameList = new ArrayList<>();
        while (generatedCharacterNameList.size() < 20) {
            Random rand = new Random(new Date().getTime());
            nameConstrainForm.setNameSize(nameSizeList.get(rand.nextInt(nameSizeList.size())));
            OrderGeneratedNameModel generatedName = nameGeneratorService.newNameFromCharacter(nameConstrainForm);
            if (Objects.isNull(generatedName)) continue;
            generatedName.setOrderId(orderId);
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : generatedCharacterNameList) {
                if (generatedName.getName().equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                generatedCharacterNameList.add(generatedName);
            }
        }
        for (OrderGeneratedNameModel temp : generatedCharacterNameList) {
            orderMapper.addGeneratedName(temp);
        }

        List<OrderGeneratedNameModel> generatedNameLibraryNameList = new ArrayList<>();
        while (generatedNameLibraryNameList.size() < 20) {
            Random rand = new Random(new Date().getTime());
            nameConstrainForm.setNameSize(nameSizeList.get(rand.nextInt(nameSizeList.size())));
            OrderGeneratedNameModel generatedName = nameGeneratorService.newNameFromNameLibrary(nameConstrainForm);
            if (Objects.isNull(generatedName)) continue;
            generatedName.setOrderId(orderId);
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : generatedNameLibraryNameList) {
                if (generatedName.getName().equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                generatedNameLibraryNameList.add(generatedName);
            }
        }
        for (OrderGeneratedNameModel temp : generatedNameLibraryNameList) {
            orderMapper.addGeneratedName(temp);
        }

        if (orderModel.getPlan().startsWith("八字")) {
            Map<String, String> querys = new HashMap<>();
            querys.put("birthday", orderModel.getBirthday());
            querys.put("hour", orderModel.getBirthdayHour());
            querys.put("ming", "");
            querys.put("minute", orderModel.getBirthdayMinute());
            querys.put("pay", "1");
            querys.put("sex", orderModel.getSex());
            querys.put("xing", orderModel.getLastname());

            Map mingpen = getMapFromAPI("/openapi/bazi/getMingpen", querys);
            MingpenModel mingpenModel = new MingpenModel();
            mingpenModel.setOrderId(orderModel.getId().toString());
            mingpenModel.setZhuxing(objToString(mingpen.get("ZhuXing")));
            mingpenModel.setTiangan(objToString(mingpen.get("TianGan")));
            mingpenModel.setDizhi(objToString(mingpen.get("DiZhi")));
            mingpenModel.setDayun(objToString(mingpen.get("DaYun")));
            mingpenModel.setYongshen(objToString(mingpen.get("YongShen")));
            mingpenModel.setXishen(objToString(mingpen.get("XiShen")));
            mingpenModel.setJishen(objToString(mingpen.get("JiShen")));
            mingpenModel.setJiaoyunshijian(objToString(mingpen.get("JiaoYunShiJian")));
            mingpenModel.setQiangruo(objToString(mingpen.get("QiangRuo")));
            mingpenModel.setWuxing(objToString(mingpen.get("WuXing")));
            mingpenModel.setMu(objToString(mingpen.get("Mu")));
            mingpenModel.setJin(objToString(mingpen.get("Jin")));
            mingpenModel.setShui(objToString(mingpen.get("Shui")));
            mingpenModel.setTu(objToString(mingpen.get("Tu")));
            mingpenModel.setHuo(objToString(mingpen.get("Huo")));
            orderMapper.addMingpen(mingpenModel);
            if (!orderModel.getPlan().startsWith("八字起名套餐1")) {
                Map mingju = getMapFromAPI("/openapi/bazi/getMingju", querys);
                MingjuModel mingjuModel = new MingjuModel();
                mingjuModel.setOrderId(orderModel.getId().toString());
                mingjuModel.setMingpen(objToString(mingju.get("MingPen")));
                mingjuModel.setXingge(objToString(mingju.get("XingGe")));
                mingjuModel.setXueli(objToString(mingju.get("XueLi")));
                mingjuModel.setCaifu(objToString(mingju.get("CaiFu")));
                mingjuModel.setCaifushiye(objToString(mingju.get("CaiFuShiYe")));
                mingjuModel.setDiwei(objToString(mingju.get("DiWei")));
                mingjuModel.setLiuqin(objToString(mingju.get("LiuQin")));
                mingjuModel.setJibing(objToString(mingju.get("JiBing")));
                mingjuModel.setShiye(objToString(mingju.get("ShiYe")));
                mingjuModel.setYiji(objToString(mingju.get("YiJi")));
                mingjuModel.setXiongzai(objToString(mingju.get("XiongZai")));
                mingjuModel.setGuansha(objToString(mingju.get("GuanSha")));
                orderMapper.addMingju(mingjuModel);
            }
        }

        return true;
    }
    
    private String objToString(Object obj) {
        return Objects.isNull(obj)?null:String.valueOf(obj);
    }

    private List<String> splitString(String str) {
        if (Objects.isNull(str)) return new ArrayList<>();
        return Arrays.asList(str.split("(　|\\s)*(,|，|　|\\s)(　|\\s)*"));
    }

    @Override
    public PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        List<OrderListVo> orderList = orderMapper.getList(DateUtils.dateToString(cal.getTime()));
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public OrderModel getDetail(String id) {
        return orderMapper.getDetail(id);
    }

    @Override
    public Boolean updateOrder(OrderModel orderModel) {
        orderModel.setUpdateTime(DateUtils.dateToString(new Date()));
        return orderMapper.updateOrder(orderModel);
    }

    @Override
    public List<OrderCommentModel> getComments(String orderId) {
        return orderMapper.getComments(orderId);
    }

    @Override
    public Boolean addComment(OrderCommentForm orderCommentForm) {
        orderMapper.updateStatus(orderCommentForm.getOrderId(), "待调整-" + orderCommentForm.getCommentCnt().toString(), DateUtils.dateToString(new Date()), false);
        return orderMapper.addComment(orderCommentForm);
    }

    @Override
    public PageInfo<OrderListVo> orderDeliveringList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderListVo> orderList = orderMapper.getListDelivering();
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public List<OrderGeneratedNameModel> getGeneratedNames(String orderId, Boolean namelibType) {
        return orderMapper.getGeneratedNames(orderId, namelibType);
    }

    @Override
    public Boolean deliverOrder(String id) {
        String status = orderMapper.getStatus(id);
        return orderMapper.updateStatus(id, status.replace("待", "已"), DateUtils.dateToString(new Date()), true);
    }

    @Override
    public MingpenModel getMingpen(String orderId) {
        return orderMapper.getMingpen(orderId);
    }

    @Override
    public MingjuModel getMingju(String orderId) {
        return orderMapper.getMingju(orderId);
    }

    private Map getMapFromAPI(String path, Map<String, String> querys){
        String host = "https://openapi.fatebox.cn";
        String method = "GET";
        String appcode = "32cf3b4f21904b27bd7877354307b724";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, headers, querys);
            String result = EntityUtils.toString(response.getEntity());
            Map mapType = JSON.parseObject(result, LinkedHashMap.class);
            return mapType;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    private String solarToLunar(String date) throws ParseException {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        today.setTime(formatter.parse(date));
        Lunar lunar = new Lunar(today);
        return lunar.cyclical() + "年" + lunar.toString();
    }



}
