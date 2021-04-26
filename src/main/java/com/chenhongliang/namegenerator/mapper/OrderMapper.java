package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.OrderModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper
{
	Integer insert(OrderModel orderModel);
}