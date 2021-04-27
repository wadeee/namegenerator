package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Boolean addOrder(OrderForm orderForm) {
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
        orderModel.setBannedPinyin(orderForm.getBannedPinyin());
        orderModel.setBannedCharacter(orderForm.getBannedCharacter());
        orderModel.setGeneration(orderForm.getGeneration());
        orderModel.setStyle(orderForm.getStyle());
        orderModel.setNotes(orderForm.getNotes());
        orderModel.setStatus("待交付");

        Date dateNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        cal.add(Calendar.HOUR, orderForm.getTillDeliveryTime());
        orderModel.setDeliveryTime(cal.getTime());
        orderMapper.insert(orderModel);

        return true;
    }
}
