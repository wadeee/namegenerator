package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.model.*;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    Boolean addOrder(OrderForm orderForm);

    PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize);

    OrderModel getDetail(String id);

    Boolean updateOrder(OrderModel orderModel);

    List<OrderCommentModel> getComments(String orderId);

    Boolean addComment(OrderCommentForm orderCommentForm);

    PageInfo<OrderListVo> orderDeliveringList(Integer pageNo, Integer pageSize);

    List<OrderGeneratedNameModel> getGeneratedNames(String orderId, Boolean namelibType);

    Boolean deliverOrder(String id);

    MingpenModel getMingpen(String orderId);

    MingjuModel getMingju(String orderId);
}
