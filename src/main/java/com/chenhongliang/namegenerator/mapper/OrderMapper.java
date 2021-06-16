package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.model.*;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer insert(OrderModel orderModel);

    List<OrderListVo> getList(String dateLine);

    Boolean updateWuxing(String orderId, String wuxing);

    OrderModel getDetail(String id);

    OrderModel getDetailByOrderNumber(String orderNumber);

    Boolean updateOrder(OrderModel orderModel);

    List<OrderCommentModel> getComments(String orderId);

    List<OrderCommentModel> getCommentsByOrderNumber(String orderNumber);

    Boolean addComment(OrderCommentForm orderCommentForm);

    String getStatus(String id);

    Boolean updateStatus(String id, String status, String date, Boolean delivered);

    List<OrderListVo> getListDelivering();

    Boolean addGeneratedName(OrderGeneratedNameModel orderGeneratedNameModel);

    List<OrderGeneratedNameModel> getGeneratedNames(String orderId, Boolean namelibType);

    Boolean addMingpen(MingpenModel mingpenModel);

    Boolean addMingju(MingjuModel mingjuModel);

    MingpenModel getMingpen(String orderId);

    MingjuModel getMingju(String orderId);

    Boolean deleteOrder(String orderId);

    Boolean finishOrder(String orderId, String resultName);

    Boolean clearGeneratedNames(String orderId);

    List<OrderRunInfoModel> getOrderRunInfo(String orderId);

    Boolean removeOrderRunInfo(String orderId);

    Boolean addOrderRunInfo(OrderRunInfoModel orderRunInfoModel);

}