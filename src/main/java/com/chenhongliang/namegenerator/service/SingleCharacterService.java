package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.vo.SingleCharacterVo;

import java.util.List;

public interface SingleCharacterService {
    List<SingleCharacterModel> findAll();

    String addCharacters(SingleCharacterVo singleCharacterVo) throws Exception;
}
