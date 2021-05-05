package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.model.OrderCommentModel;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import com.chenhongliang.namegenerator.service.OrderService;
import com.chenhongliang.namegenerator.util.FortuneTellingUtils;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (orderForm.getNameSize().indexOf("二字名") >= 0) {
            nameSizeList.add(1);
        }
        if (orderForm.getNameSize().indexOf("三字名") >= 0) {
            nameSizeList.add(2);
        }
        if (orderForm.getNameSize().indexOf("四字名") >= 0) {
            nameSizeList.add(3);
        }
        nameConstrainForm.setGeneration(orderForm.getGeneration());
        nameConstrainForm.setWuxing(Arrays.asList(orderModel.getWuxing().split(" ")));
        nameConstrainForm.setBannedCharacter(splitString(orderForm.getBannedCharacter()));
        nameConstrainForm.setBannedPinyin(splitString(orderForm.getBannedPinyin()));
        List<OrderGeneratedNameModel> generatedCharacterNameList = new ArrayList<>();
//        while (generatedCharacterNameList.size() < 20) {
//            Random rand = new Random(new Date().getTime());
//            nameConstrainForm.setNameSize(nameSizeList.get(rand.nextInt(nameSizeList.size())));
//            OrderGeneratedNameModel generatedName = nameGeneratorService.newNameFromCharacter(nameConstrainForm);
//            generatedName.setOrderId(orderId);
//            Boolean flag = true;
//            for (OrderGeneratedNameModel temp : generatedCharacterNameList) {
//                if (generatedName.getName().equals(temp.getName())) {
//                    flag = false;
//                }
//            }
//            if (flag) {
//                generatedCharacterNameList.add(generatedName);
//            }
//        }
//        for (OrderGeneratedNameModel temp : generatedCharacterNameList) {
//            orderMapper.addGeneratedName(temp);
//        }

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
                generatedCharacterNameList.add(generatedName);
            }

        }
        for (OrderGeneratedNameModel temp : generatedNameLibraryNameList) {
            orderMapper.addGeneratedName(temp);
        }

        return true;
    }

    private List<String> splitString(String str) {
        if (Objects.isNull(str)) return new ArrayList<>();
        return Arrays.asList(str.split("(　|\\s)*(,|，|　|\\s)(　|\\s)*"));
    }

    @Override
    public PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderListVo> orderList = orderMapper.getList();
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public OrderModel getDetail(String id) {
        return orderMapper.getDetail(id);
    }

    @Override
    public Boolean updateOrder(OrderModel orderModel) {
        return orderMapper.updateOrder(orderModel);
    }

    @Override
    public List<OrderCommentModel> getComments(String orderId) {
        return orderMapper.getComments(orderId);
    }

    @Override
    public Boolean addComment(OrderCommentForm orderCommentForm) {
        orderMapper.updateStatus(orderCommentForm.getOrderId(), "调整-" + orderCommentForm.getCommentCnt().toString());
        return orderMapper.addComment(orderCommentForm);
    }

    @Override
    public PageInfo<OrderListVo> orderDeliveringList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderListVo> orderList = orderMapper.getListByStatus("待交付");
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public PageInfo<OrderListVo> orderTrimmingList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderListVo> orderList = orderMapper.getListByStatus("调整");
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }


}
