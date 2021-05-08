package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.exception.NoCharacterException;
import com.chenhongliang.namegenerator.exception.NotCorrectSizeException;
import com.chenhongliang.namegenerator.form.SingleCharacterManageForm;
import com.chenhongliang.namegenerator.mapper.SingleCharacterManageMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.SingleCharacterManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (character.length() != 1) {
            throw new NotCorrectSizeException();
        }
        System.out.println(character);
        System.out.println(singleCharacterMapper.isExist(character));
        if (!singleCharacterMapper.isExist(character)) {
            throw new NoCharacterException();
        }
        SingleCharacterModel result = singleCharacterManageMapper.selectByCharacter(character);
        SingleCharacterManageForm singleCharacterManageForm = new SingleCharacterManageForm();
        singleCharacterManageForm.setCharacter(result.getCharacter());
        singleCharacterManageForm.setMeaning(result.getMeaning());
        singleCharacterManageForm.setPoetry(result.getPoetry());
        singleCharacterManageForm.setWuxing(result.getWuxing());
        singleCharacterManageForm.setPinyin(result.getPinyin());
        singleCharacterManageForm.setMale(result.getMale());
        singleCharacterManageForm.setFemale(result.getFemale());
        singleCharacterManageForm.setIdiom(result.getIdiom());
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

    @Override
    public List<String> allCharacters() {
        return singleCharacterManageMapper.allCharacters();
    }
}
