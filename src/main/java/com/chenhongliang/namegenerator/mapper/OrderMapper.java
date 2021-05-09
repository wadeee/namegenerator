package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.model.OrderCommentModel;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer insert(OrderModel orderModel);

    List<OrderListVo> getList(String dateLine);

    OrderModel getDetail(String id);

    Boolean updateOrder(OrderModel orderModel);

    List<OrderCommentModel> getComments(String orderId);

    Boolean addComment(OrderCommentForm orderCommentForm);

    String getStatus(String id);

    Boolean updateStatus(String id, String status, String date, Boolean delivered);

    List<OrderListVo> getListDelivering();

    Boolean addGeneratedName(OrderGeneratedNameModel orderGeneratedNameModel);

    List<OrderGeneratedNameModel> getGeneratedNames(String orderId, Boolean namelibType);
}