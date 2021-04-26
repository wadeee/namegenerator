package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.NameLibraryManageForm;
import com.chenhongliang.namegenerator.service.NameLibraryManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/name-library-manage")
public class NameLibraryManageController {

    @Autowired
    private NameLibraryManageService nameLibraryManageService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("nameAmount", nameLibraryManageService.getNameAmount());
        return "nameLibraryManage/index";
    }

    @GetMapping("/get-amount")
    @ResponseBody
    public Map<String, Integer> amount() {
        Map<String, Integer> result = new HashMap<>();
        result.put("amount", nameLibraryManageService.getNameAmount());
        return result;
    }

    @PostMapping
    @ResponseBody
    public NameLibraryManageForm getNameInfo(@RequestBody Map<String, String> searchInfo) {
        return nameLibraryManageService.getNameInfo(searchInfo.get("name"));
    }

    @PostMapping("/update")
    @ResponseBody
    public Integer updateNameInfo(@RequestBody NameLibraryManageForm nameLibraryManageForm) {
        return nameLibraryManageService.updateNameInfo(nameLibraryManageForm);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Integer deleteName(@RequestBody Map<String, String> searchInfo) {
        return nameLibraryManageService.deleteNameInfo(searchInfo.get("name"));
    }

}
