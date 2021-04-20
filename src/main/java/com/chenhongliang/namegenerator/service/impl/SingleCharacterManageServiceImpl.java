package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.SingleCharacterManageForm;
import com.chenhongliang.namegenerator.mapper.SingleCharacterManageMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.SingleCharacterManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleCharacterManageServiceImpl implements SingleCharacterManageService {

    @Autowired
    private SingleCharacterManageMapper singleCharacterManageMapper;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Override
    public Integer getCharacterAmount() {
        return singleCharacterManageMapper.allAmount();
    }

    @Override
    public SingleCharacterManageForm getCharacterInfo(String character) {
        System.out.println(character);
        if (!singleCharacterMapper.isExist(character)) {
            return null;
        }
        SingleCharacterModel result = singleCharacterManageMapper.selectByCharacter(character);
        System.out.println(result.toString());
        SingleCharacterManageForm singleCharacterManageForm = new SingleCharacterManageForm();
        singleCharacterManageForm.setCharacter(result.getCharacter());
        singleCharacterManageForm.setMeaning(result.getMeaning());
        singleCharacterManageForm.setPoetry(result.getPoetry());
        singleCharacterManageForm.setWuxing(result.getWuxing());
        singleCharacterManageForm.setPinyin(result.getPinyin());
        singleCharacterManageForm.setBoy(result.getBoy());
        singleCharacterManageForm.setGirl(result.getGirl());
        singleCharacterManageForm.setIdiom(result.getIdiom());
        System.out.println(singleCharacterManageForm.toString());
        return singleCharacterManageForm;
    }

    @Override
    public Integer updateCharacterInfo(SingleCharacterManageForm singleCharacterManageForm) {

        return singleCharacterManageMapper.updateCharacter(singleCharacterManageForm);
    }

    @Override
    public Integer deleteCharacterInfo(String character) {
        return singleCharacterManageMapper.deleteCharacter(character);
    }
}
