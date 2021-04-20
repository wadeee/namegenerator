package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.SingleCharacterForm;

import java.util.List;
import java.util.Map;

public interface SingleCharacterService {
    Map<String, List<String>> addCharacters(SingleCharacterForm singleCharacterForm) throws Exception;

    String updatePinyin(Map<String, String> pinyinMap);
}
