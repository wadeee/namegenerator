package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.SingleCharacterForm;
import com.chenhongliang.namegenerator.vo.AddSingleCharacterResultVo;

import java.util.Map;

public interface SingleCharacterService {
    AddSingleCharacterResultVo addCharacters(SingleCharacterForm singleCharacterForm) throws Exception;

    String updatePinyin(Map<String, String> pinyinMap);
}
