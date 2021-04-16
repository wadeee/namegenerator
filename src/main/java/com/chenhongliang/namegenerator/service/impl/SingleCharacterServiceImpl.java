package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingleCharacterServiceImpl implements SingleCharacterService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Override
    public List<SingleCharacterModel> findAll() {
        return singleCharacterMapper.findAll();
    }
}
