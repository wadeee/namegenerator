package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.service.SingleCharacterService;
import com.chenhongliang.namegenerator.vo.SingleCharacterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("singleCharacter")
public class SingleCharacterController {

    @Autowired
    private SingleCharacterService singleCharacterService;

    @GetMapping
    public String index() {
        return "singleCharacter/index";
    }

    @PostMapping
    @ResponseBody
    public String add(@RequestBody SingleCharacterVo singleCharacterVo) throws Exception {
        return singleCharacterService.addCharacters(singleCharacterVo);
    }


}
