package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String index() {
        return "order/index";
    }

    @PostMapping
    @ResponseBody
    public Boolean add(@RequestBody OrderForm orderForm) {
        return orderService.addOrder(orderForm);
    }

}
