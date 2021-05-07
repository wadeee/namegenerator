package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.SingleCharacterManageForm;

import java.util.List;

public interface SingleCharacterManageService {

    Integer getCharacterAmount();

    SingleCharacterManageForm getCharacterInfo(String character);

    Integer updateCharacterInfo(SingleCharacterManageForm singleCharacterManageForm);

    Integer deleteCharacterInfo(String character);

    List<String> allCharacters();
}
