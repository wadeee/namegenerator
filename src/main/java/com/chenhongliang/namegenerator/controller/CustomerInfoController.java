package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.CustomerInfoForm;
import com.chenhongliang.namegenerator.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


}
