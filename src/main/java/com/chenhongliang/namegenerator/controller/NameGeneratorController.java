package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/name-generator")
public class NameGeneratorController {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/characters/{orderId}")
    @ResponseBody
    public List<OrderGeneratedNameModel> fromCharacters(@PathVariable("orderId") String orderId) {
        OrderModel orderModel = orderMapper.getDetail(orderId);

        NameConstrainForm nameConstrainForm = new NameConstrainForm();
        nameConstrainForm.setLastname(orderModel.getLastname());
        nameConstrainForm.setSex(orderModel.getSex());
        List<Integer> nameSizeList = new ArrayList<>();
        if (orderModel.getNameSize().contains("二字名")) {
            nameSizeList.add(1);
        }
        if (orderModel.getNameSize().contains("三字名")) {
            nameSizeList.add(2);
        }
        if (orderModel.getNameSize().contains("四字名")) {
            nameSizeList.add(3);
        }
        nameConstrainForm.setNameSize(nameSizeList);
        nameConstrainForm.setGeneration(orderModel.getGeneration());
        if (!Objects.isNull(orderModel.getWuxing())) {
            nameConstrainForm.setWuxing(Arrays.asList(orderModel.getWuxing().split(" ")));
        }
        nameConstrainForm.setBannedCharacter(splitString(orderModel.getBannedCharacter()));
        nameConstrainForm.setBannedPinyin(splitString(orderModel.getBannedPinyin()));

        List<OrderGeneratedNameModel> orderGeneratedNameModelList = orderMapper.getGeneratedNames(orderId, false);
        List<OrderGeneratedNameModel> addList = new ArrayList<>();
        while (addList.size() < 20) {
            String generatedName = nameGeneratorService.newNameFromCharacter(nameConstrainForm);
            if (Objects.isNull(generatedName)) continue;
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                if (generatedName.equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                OrderGeneratedNameModel generatedNameModel = nameGeneratorService.getNameInfoFromCharacter(generatedName);
                generatedNameModel.setOrderId(Integer.parseInt(orderId));
                orderGeneratedNameModelList.add(generatedNameModel);
                addList.add(generatedNameModel);
            }
        }
        for (OrderGeneratedNameModel temp : addList) {
            orderMapper.addGeneratedName(temp);
        }
        return orderGeneratedNameModelList;
    }

    @GetMapping("/name-library/{orderId}")
    @ResponseBody
    public List<OrderGeneratedNameModel> fromNameLibrary(@PathVariable("orderId") String orderId) {
        OrderModel orderModel = orderMapper.getDetail(orderId);

        NameConstrainForm nameConstrainForm = new NameConstrainForm();
        nameConstrainForm.setLastname(orderModel.getLastname());
        nameConstrainForm.setSex(orderModel.getSex());
        List<Integer> nameSizeList = new ArrayList<>();
        if (orderModel.getNameSize().contains("二字名")) {
            nameSizeList.add(1);
        }
        if (orderModel.getNameSize().contains("三字名")) {
            nameSizeList.add(2);
        }
        if (orderModel.getNameSize().contains("四字名")) {
            nameSizeList.add(3);
        }
        nameConstrainForm.setNameSize(nameSizeList);
        nameConstrainForm.setGeneration(orderModel.getGeneration());
        if (!Objects.isNull(orderModel.getWuxing())) {
            nameConstrainForm.setWuxing(Arrays.asList(orderModel.getWuxing().split(" ")));
        }
        nameConstrainForm.setBannedCharacter(splitString(orderModel.getBannedCharacter()));
        nameConstrainForm.setBannedPinyin(splitString(orderModel.getBannedPinyin()));

        List<OrderGeneratedNameModel> orderGeneratedNameModelList = orderMapper.getGeneratedNames(orderId, true);
        List<OrderGeneratedNameModel> addList = new ArrayList<>();
        while (addList.size() < 20) {
            String generatedName = nameGeneratorService.newNameFromNameLibrary(nameConstrainForm);
            if (Objects.isNull(generatedName)) continue;
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                if (generatedName.equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                OrderGeneratedNameModel generatedNameModel = nameGeneratorService.getNameInfoFromNameLibrary(generatedName);
                generatedNameModel.setOrderId(Integer.parseInt(orderId));
                orderGeneratedNameModelList.add(generatedNameModel);
                addList.add(generatedNameModel);
            }
        }
        for (OrderGeneratedNameModel temp : addList) {
            orderMapper.addGeneratedName(temp);
        }
        return orderGeneratedNameModelList;
    }

    private List<String> splitString(String str) {
        if (Objects.isNull(str)) return new ArrayList<>();
        return Arrays.asList(str.split("(　|\\s)*(,|，|　|\\s)(　|\\s)*"));
    }
}
