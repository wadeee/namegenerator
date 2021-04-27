package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.vo.OrderListVo;

import java.util.List;

public interface OrderService {

    Boolean addOrder(OrderForm orderForm);

    List<OrderListVo> orderList();

    OrderModel getDetail(String id);

    Boolean updateOrder(OrderModel orderModel);
}
