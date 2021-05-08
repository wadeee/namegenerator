package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/single-character")
public class SingleCharacterController {

    @Autowired
    private SingleCharacterService singleCharacterService;

    @GetMapping
    public String index() {
        return "singleCharacter/index";
    }

    @PostMapping
    @ResponseBody
    public SingleCharacterModel add(@RequestBody Map<String, String> searchInfo) throws Exception {
        return singleCharacterService.addCharacter(searchInfo.get("character"));
    }

    @PostMapping("/pinyin")
    @ResponseBody
    public String pinyin(@RequestBody Map<String, String> pinyinMap) {
        singleCharacterService.updatePinyin(pinyinMap);
        return "success";
    }

}
