package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.NameLibraryForm;
import com.chenhongliang.namegenerator.form.NameLibraryPinyinForm;
import com.chenhongliang.namegenerator.service.NameLibraryService;
import com.chenhongliang.namegenerator.vo.AddNameLibraryResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/name-library")
public class NameLibraryController {

    @Autowired
    private NameLibraryService nameLibraryService;

    @GetMapping
    public String index() {
        return "nameLibrary/index";
    }

    @PostMapping
    @ResponseBody
    public AddNameLibraryResultVo add(@RequestBody NameLibraryForm nameLibraryForm) {
        return nameLibraryService.addName(nameLibraryForm);
    }

    @PostMapping("/pinyin")
    @ResponseBody
    public Boolean pinyin(@RequestBody NameLibraryPinyinForm nameLibraryPinyinForm) {
        return nameLibraryService.updatePinyin(nameLibraryPinyinForm);
    }

}
