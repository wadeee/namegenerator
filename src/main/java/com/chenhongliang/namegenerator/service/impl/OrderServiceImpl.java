package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.model.OrderCommentModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.OrderService;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        orderModel.setBirthdayHour(orderForm.getBirthdayHour());
        orderModel.setBirthdayMinute(orderForm.getBirthdayMinute());
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

    @Override
    public PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
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
        PageHelper.startPage(pageNo,pageSize);
        List<OrderListVo> orderList = orderMapper.getListByStatus("待交付");
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public PageInfo<OrderListVo> orderTrimmingList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<OrderListVo> orderList = orderMapper.getListByStatus("调整");
        PageInfo<OrderListVo> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }


}
