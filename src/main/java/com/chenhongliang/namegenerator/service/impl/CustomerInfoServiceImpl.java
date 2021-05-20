package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.CustomerInfoForm;
import com.chenhongliang.namegenerator.mapper.CustomerInfoMapper;
import com.chenhongliang.namegenerator.model.CustomerInfoModel;
import com.chenhongliang.namegenerator.service.CustomerInfoService;
import com.chenhongliang.namegenerator.util.DateStringUtils;
import com.chenhongliang.namegenerator.vo.CustomerInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoMapper customerInfoMapper;


    @Override
    public Boolean insert(CustomerInfoForm customerInfoForm) {
        CustomerInfoModel customerInfoModel = new CustomerInfoModel();
        customerInfoModel.setWechat(customerInfoForm.getWechat());
        customerInfoModel.setWechatMachine(customerInfoForm.getWechatMachine());
        customerInfoModel.setSalesman(customerInfoForm.getSalesman());
        customerInfoModel.setBirthday(customerInfoForm.getBirthday());

        Date birthday = DateStringUtils.stringToDate(customerInfoForm.getBirthday());
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        cal.add(Calendar.DATE, -2);

        customerInfoModel.setVisitDate(DateStringUtils.dateToString(cal.getTime()));

        return customerInfoMapper.insert(customerInfoModel);
    }

    @Override
    public PageInfo<CustomerInfoVo> cutomerInfoList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<CustomerInfoVo> customerInfoVoList = customerInfoMapper.getList(DateStringUtils.dateToString(new Date()));
        PageInfo<CustomerInfoVo> pageInfo = new PageInfo<>(customerInfoVoList);
        return pageInfo;
    }

    @Override
    public Boolean delete(String id) {
        return customerInfoMapper.delete(id);
    }

    @Override
    public Integer getVisitCnt() {
        return customerInfoMapper.getVisitCnt(DateStringUtils.dateToString(new Date()));
    }
}
