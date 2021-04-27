package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.OrderService;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/list")
    public String list() {
        return "order/list";
    }

    @PostMapping("/list-data")
    @ResponseBody
    public PageInfo<OrderListVo> listData(@RequestBody Map<String, Integer> requestInfo) {
        return orderService.orderList(requestInfo.get("pageNo"), 17);
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public OrderModel detail(@PathVariable("id") String id) {
        return orderService.getDetail(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public Boolean detail(@RequestBody OrderModel orderModel) {
        return orderService.updateOrder(orderModel);
    }


}
