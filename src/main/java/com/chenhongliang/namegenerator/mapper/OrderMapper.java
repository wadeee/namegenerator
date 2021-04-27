package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper
{
	Integer insert(OrderModel orderModel);

	List<OrderListVo> getList();

	OrderModel getDetail(String id);

	Boolean updateOrder(OrderModel orderModel);
}