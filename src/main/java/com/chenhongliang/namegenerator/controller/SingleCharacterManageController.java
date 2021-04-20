package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.SingleCharacterManageForm;
import com.chenhongliang.namegenerator.service.SingleCharacterManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/single-character-manage")
public class SingleCharacterManageController {

    @Autowired
    private SingleCharacterManageService singleCharacterManageService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("characterAmount", singleCharacterManageService.getCharacterAmount());
        return "singleCharacterManage/index";
    }

    @PostMapping
    @ResponseBody
    public SingleCharacterManageForm getCharacterInfo(@RequestBody Map<String, String> searchInfo) {
        return singleCharacterManageService.getCharacterInfo(searchInfo.get("character"));
    }

    @PostMapping("/update")
    @ResponseBody
    public Integer updateCharacterInfo(@RequestBody SingleCharacterManageForm singleCharacterManageForm) {
        return singleCharacterManageService.updateCharacterInfo(singleCharacterManageForm);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Integer deleteCharacter(@RequestBody Map<String, String> searchInfo) {
        return singleCharacterManageService.deleteCharacterInfo(searchInfo.get("character"));
    }

}
