package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.service.SingleCharacterService;
import com.chenhongliang.namegenerator.form.SingleCharacterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Map<String, List<String>> add(@RequestBody SingleCharacterForm singleCharacterForm) throws Exception {
        return singleCharacterService.addCharacters(singleCharacterForm);
    }

    @PostMapping("/pinyin")
    @ResponseBody
    public String pinyin(@RequestBody Map<String, String> pinyinMap) {
        System.out.println(pinyinMap.toString());
        singleCharacterService.updatePinyin(pinyinMap);
        return "success";
    }

}
