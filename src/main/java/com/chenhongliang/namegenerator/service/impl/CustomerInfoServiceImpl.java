package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.CustomerInfoForm;
import com.chenhongliang.namegenerator.mapper.CustomerInfoMapper;
import com.chenhongliang.namegenerator.mapper.FollowMapper;
import com.chenhongliang.namegenerator.model.CustomerInfoModel;
import com.chenhongliang.namegenerator.service.CustomerInfoService;
import com.chenhongliang.namegenerator.util.DateStringUtils;
import com.chenhongliang.namegenerator.vo.CustomerInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private FollowMapper followMapper;

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

    @Override
    public Map<String, Integer> getSalesmanCount() {
        Map<String, Integer> result = new LinkedHashMap<>();
        Calendar cal = Calendar.getInstance();
        Integer month = cal.get(Calendar.MONTH) + 1;
        Integer year = cal.get(Calendar.YEAR);
        result.put("肖鑫", nullToInt(followMapper.getCount(year, month, "肖鑫")));
        result.put("婷婷", nullToInt(followMapper.getCount(year, month, "婷婷")));
        return result;
    }

    private Integer nullToInt(Integer origin) {
        return Objects.isNull(origin)?0:origin;
    }
}
