package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private SingleCharacterService singleCharacterService;

    @GetMapping
    public String index() {
        System.out.println(1);
        List<SingleCharacterModel>  result = singleCharacterService.findAll();
        for (SingleCharacterModel a: result) {
            System.out.println(a.toString());
        }
        return "/index";
    }


}
