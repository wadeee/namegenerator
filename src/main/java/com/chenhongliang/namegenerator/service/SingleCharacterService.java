package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;

import java.util.Map;

public interface SingleCharacterService {
    SingleCharacterModel addCharacter(String character) throws Exception;

    String updatePinyin(Map<String, String> pinyinMap);
}
