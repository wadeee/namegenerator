package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.OrderCommentForm;
import com.chenhongliang.namegenerator.form.OrderForm;
import com.chenhongliang.namegenerator.model.MingjuModel;
import com.chenhongliang.namegenerator.model.MingpenModel;
import com.chenhongliang.namegenerator.model.OrderCommentModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.OrderService;
import com.chenhongliang.namegenerator.vo.OrderListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> add(@RequestBody OrderForm orderForm) {
        return orderService.addOrder(orderForm);
    }

    @PostMapping("updateWuxing/{id}")
    @ResponseBody
    public Boolean updateWuxings(@PathVariable("id") String id, @RequestBody List<String> wuxing) {
        return orderService.updateWuxing(id, wuxing);
    }

    @GetMapping("/list")
    public String list() {
        return "order/list";
    }

    @PostMapping("/list-data")
    @ResponseBody
    public PageInfo<OrderListVo> listData(@RequestBody Map<String, Integer> requestInfo) {
        return orderService.orderList(requestInfo.get("pageNo"), 16);
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public OrderModel detail(@PathVariable("id") String id) {
        return orderService.getDetail(id);
    }

    @GetMapping("/detail-by-orderNumber/{orderNumber}")
    @ResponseBody
    public OrderModel detailByOrderNumber(@PathVariable("orderNumber") String orderNumber) {
        return orderService.getDetailByOrderNumber(orderNumber);
    }

    @PostMapping("/update")
    @ResponseBody
    public Boolean update(@RequestBody OrderModel orderModel) {
        return orderService.updateOrder(orderModel);
    }

    @GetMapping("/comments/{id}")
    @ResponseBody
    public List<OrderCommentModel> comments(@PathVariable("id") String id) {
        return orderService.getComments(id);
    }

    @GetMapping("/comments-by-orderNumbner/{orderNumber}")
    @ResponseBody
    public List<OrderCommentModel> commentsByOrderNumber(@PathVariable("orderNumber") String orderNumber) {
        return orderService.getCommentsByOrderNumber(orderNumber);
    }

    @PostMapping("/comments/add")
    @ResponseBody
    public Boolean addComment(@RequestBody OrderCommentForm orderCommentForm) {
        return orderService.addComment(orderCommentForm);
    }

    @GetMapping("/list/delivering")
    public String deliveringList() {
        return "order/deliveringList";
    }

    @PostMapping("/list-data/delivering")
    @ResponseBody
    public PageInfo<OrderListVo> listDataDelivering(@RequestBody Map<String, Integer> requestInfo) {
        return orderService.orderDeliveringList(requestInfo.get("pageNo"), 16);
    }

    @GetMapping("/run/{id}")
    public String run(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "order/run";
    }

    @GetMapping("/run/detail/{id}")
    @ResponseBody
    public Map<String, Object> runDetail(@PathVariable("id") String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderService.getDetail(id));
        result.put("comments", orderService.getComments(id));
        result.put("generatedCharacterNames", orderService.getGeneratedNames(id, false));
        result.put("generatedNameLibraryNames", orderService.getGeneratedNames(id, true));
        return result;
    }

    @GetMapping("/deliver/{id}")
    @ResponseBody
    public Boolean deliver(@PathVariable("id") String id) {
        return orderService.deliverOrder(id);
    }

    @GetMapping("/mingpen/{id}")
    @ResponseBody
    public MingpenModel mingpen(@PathVariable("id") String id) {
        return orderService.getMingpen(id);
    }

    @GetMapping("/mingju/{id}")
    @ResponseBody
    public MingjuModel mingju(@PathVariable("id") String id) {
        return orderService.getMingju(id);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Boolean deleteOrder(@PathVariable("id") String id) {
        return orderService.deleteOrder(id);
    }

    @PostMapping("/finish/{id}")
    @ResponseBody
    public Boolean finishOrder(@PathVariable("id") String id, @RequestBody Map<String, String> info) {
        return orderService.finishOrder(id, info.get("resultName"));
    }

}
