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
import com.chenhongliang.namegenerator.util.DateUtils;
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

        return true;
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
        cal.add(Calendar.DATE, -2);
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


}
