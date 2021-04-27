package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageInfo;

public interface OrderService {

    Boolean addOrder(OrderForm orderForm);

    PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize);

    OrderModel getDetail(String id);

    Boolean updateOrder(OrderModel orderModel);
}
