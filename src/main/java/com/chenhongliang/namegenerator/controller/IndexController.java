package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    CustomerInfoService customerInfoService;

    @GetMapping
    public String index() {
        return "/index";
    }

    @GetMapping("/getVisitCnt")
    @ResponseBody
    public Integer getVisitCnt() {
        return customerInfoService.getVisitCnt();
    }


}
