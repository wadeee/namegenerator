package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.mapper.NameLibraryMapper;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/name-generator")
public class NameGeneratorController {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @GetMapping("/characters/{orderId}")
    @ResponseBody
    public List<OrderGeneratedNameModel> fromCharacters(@PathVariable("orderId") String orderId) {
        return nameGeneratorService.newNamesFromCharacter(orderId);
    }

    @GetMapping("/name-library/{orderId}")
    @ResponseBody
    public List<OrderGeneratedNameModel> fromNameLibrary(@PathVariable("orderId") String orderId) {
        return nameGeneratorService.newNamesFromNameLibrary(orderId);
    }

}
