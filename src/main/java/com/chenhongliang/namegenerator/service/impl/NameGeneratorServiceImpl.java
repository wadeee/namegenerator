package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.mapper.NameLibraryManageMapper;
import com.chenhongliang.namegenerator.mapper.NameLibraryMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterManageMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import com.chenhongliang.namegenerator.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NameGeneratorServiceImpl implements NameGeneratorService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private SingleCharacterManageMapper singleCharacterManageMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @Autowired
    private NameLibraryManageMapper nameLibraryManageMapper;

    static Random rand = new Random(new Date().getTime());

    @Override
    public String newNameFromCharacter(NameConstrainForm nameConstrainForm, Map<String, List<String>> wuxingToCharactersMap) {
        Integer nameSize = RandomUtils.randomSelect(nameConstrainForm.getNameSize());
        List<String> wuxingOrder;
        if (Objects.isNull(nameConstrainForm.getWuxing()) || nameConstrainForm.getWuxing().isEmpty()) {
            switch (nameSize) {
                case 1:
                    return RandomUtils.randomSelect(wuxingToCharactersMap.get(""));
                case 2:
                    return RandomUtils.randomSelect(wuxingToCharactersMap.get("")) + RandomUtils.randomSelect(wuxingToCharactersMap.get(""));
                case 3:
                    return RandomUtils.randomSelect(wuxingToCharactersMap.get("")) + RandomUtils.randomSelect(wuxingToCharactersMap.get("")) + RandomUtils.randomSelect(wuxingToCharactersMap.get(""));
            }
        } else {
            switch (nameSize) {
                case 1:
                    return RandomUtils.randomSelect(wuxingToCharactersMap.get(RandomUtils.randomSelect(nameConstrainForm.getWuxing())));
                case 2:
                    wuxingOrder = disorder(nameConstrainForm.getWuxing(), nameSize);
                    return RandomUtils.randomSelect(wuxingToCharactersMap.get(wuxingOrder.get(0))) + RandomUtils.randomSelect(wuxingToCharactersMap.get(wuxingOrder.get(1)));
                case 3:
                    wuxingOrder = disorder(nameConstrainForm.getWuxing(), nameSize);
                    return RandomUtils.randomSelect(wuxingToCharactersMap.get(wuxingOrder.get(0))) + RandomUtils.randomSelect(wuxingToCharactersMap.get(wuxingOrder.get(1))) + RandomUtils.randomSelect(wuxingToCharactersMap.get(wuxingOrder.get(2)));
            }
        }
        return null;
    }

    @Override
    public OrderGeneratedNameModel getNameInfoFromCharacter(String name) {
        OrderGeneratedNameModel orderGeneratedNameModel = new OrderGeneratedNameModel();
        orderGeneratedNameModel.setName(name);
        orderGeneratedNameModel.setPinyin("");
        orderGeneratedNameModel.setWuxing("");
        orderGeneratedNameModel.setMeaning("");
        orderGeneratedNameModel.setSource("");
        orderGeneratedNameModel.setNamelibType(false);
        for (int i = 0; i < name.length(); i++) {
            SingleCharacterModel singleCharacterModel = singleCharacterManageMapper.selectByCharacter(name.substring(i, i+1));
            if (i==0) {
                orderGeneratedNameModel.setPinyin(singleCharacterModel.getPinyin());
                orderGeneratedNameModel.setWuxing(singleCharacterModel.getWuxing());
                orderGeneratedNameModel.setMeaning(singleCharacterModel.getMeaning());
                orderGeneratedNameModel.setSource(singleCharacterModel.getIdiom() + "\n" + singleCharacterModel.getPoetry());
            } else {
                orderGeneratedNameModel.setPinyin(orderGeneratedNameModel.getName() + " " + singleCharacterModel.getPinyin());
                orderGeneratedNameModel.setWuxing(orderGeneratedNameModel.getWuxing() + " " + singleCharacterModel.getWuxing());
                orderGeneratedNameModel.setMeaning(orderGeneratedNameModel.getMeaning() + "\n" + singleCharacterModel.getMeaning());
                orderGeneratedNameModel.setSource(orderGeneratedNameModel.getSource() + "\n" + singleCharacterModel.getIdiom() + "\n" + singleCharacterModel.getPoetry());
            }
        }
        return orderGeneratedNameModel;
    }

    @Override
    public OrderGeneratedNameModel getNameInfoFromNameLibrary(String name) {
        NameLibraryModel nameLibraryModel = nameLibraryManageMapper.selectByName(name);
        OrderGeneratedNameModel orderGeneratedNameModel = new OrderGeneratedNameModel();
        orderGeneratedNameModel.setName(name);
        orderGeneratedNameModel.setNamelibType(true);
        orderGeneratedNameModel.setPinyin(nameLibraryModel.getPinyin());
        orderGeneratedNameModel.setWuxing(nameLibraryModel.getWuxing());
        orderGeneratedNameModel.setMeaning(nameLibraryModel.getMeaning());
        orderGeneratedNameModel.setSource(nameLibraryModel.getSource());
        return orderGeneratedNameModel;
    }

    private List<String> disorder(List<String> wuxing, Integer nameSize) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nameSize; i++) {
            result.add(RandomUtils.randomSelect(wuxing));
        }
        Set<String> resultSet = new HashSet<>(result);
        if (wuxing.size()>1 && resultSet.size()<=1) return disorder(wuxing, nameSize);
        return result;
    }
}
