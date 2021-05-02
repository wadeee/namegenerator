package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.model.OrderCommentModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer insert(OrderModel orderModel);

    List<OrderListVo> getList();

    OrderModel getDetail(String id);

    Boolean updateOrder(OrderModel orderModel);

    List<OrderCommentModel> getComments(String orderId);

    Boolean addComment(OrderCommentForm orderCommentForm);

    Boolean updateStatus(String id, String status);

    List<OrderListVo> getListByStatus(String status);
}