package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.CustomerInfoForm;
import com.chenhongliang.namegenerator.service.CustomerInfoService;
import com.chenhongliang.namegenerator.vo.CustomerInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/customer-info")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @GetMapping
    public String index() {
        return "customerInfo/index";
    }

    @PostMapping("/add")
    @ResponseBody
    public Boolean index(@RequestBody CustomerInfoForm customerInfoForm) {
        return customerInfoService.insert(customerInfoForm);
    }

    @GetMapping("/list")
    public String list() {
        return "customerInfo/list";
    }

    @PostMapping("/list-data")
    @ResponseBody
    public PageInfo<CustomerInfoVo> listData(@RequestBody Map<String, Integer> requestInfo) {
        return customerInfoService.cutomerInfoList(requestInfo.get("pageNo"), 16);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Boolean listData(@PathVariable("id") String id) {
        return customerInfoService.delete(id);
    }


}
