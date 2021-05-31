package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.CustomerInfoForm;
import com.chenhongliang.namegenerator.vo.CustomerInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface CustomerInfoService {

    Boolean insert(CustomerInfoForm customerInfoForm);

    PageInfo<CustomerInfoVo> cutomerInfoList(Integer pageNo, Integer pageSize);

    Boolean delete(String id);

    Integer getVisitCnt();

    Map<String, Integer> getSalesmanCount();
}
