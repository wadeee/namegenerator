package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.model.*;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Map<String, Object> addOrder(OrderForm orderForm);

    Boolean updateWuxing(String id, List<String> wuxing);

    PageInfo<OrderListVo> orderList(Integer pageNo, Integer pageSize);

    OrderModel getDetail(String id);

    OrderModel getDetailByOrderNumber(String orderNumber);

    Boolean updateOrder(OrderModel orderModel);

    List<OrderCommentModel> getComments(String orderId);

    List<OrderCommentModel> getCommentsByOrderNumber(String orderNumber);

    Boolean addComment(OrderCommentForm orderCommentForm);

    PageInfo<OrderListVo> orderDeliveringList(Integer pageNo, Integer pageSize);

    List<OrderGeneratedNameModel> getGeneratedNames(String orderId, Boolean namelibType);

    Boolean deliverOrder(String id);

    MingpenModel getMingpen(String orderId);

    MingjuModel getMingju(String orderId);

    Boolean deleteOrder(String orderId);

    Boolean finishOrder(String orderId, String resultName);
}
