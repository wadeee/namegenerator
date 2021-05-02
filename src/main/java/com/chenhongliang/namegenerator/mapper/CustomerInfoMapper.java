package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.CustomerInfoModel;
import com.chenhongliang.namegenerator.vo.CustomerInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerInfoMapper {

    Boolean insert(CustomerInfoModel customerInfoModel);

    List<CustomerInfoVo> getList();

    Boolean delete(String id);

    Integer getVisitCnt(String visitDate);

}