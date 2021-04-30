package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.CustomerInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerInfoMapper {

    Boolean insert(CustomerInfoModel customerInfoModel);

}