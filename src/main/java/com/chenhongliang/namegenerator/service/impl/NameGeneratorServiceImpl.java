package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.mapper.NameLibraryMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class NameGeneratorServiceImpl implements NameGeneratorService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @Override
    public OrderGeneratedNameModel newNameFromCharacter(NameConstrainForm nameConstrainForm) {
        List<SingleCharacterModel> constrainedCharacters = singleCharacterMapper.constrainedCharacters(nameConstrainForm);
        OrderGeneratedNameModel result = new OrderGeneratedNameModel();
        result.setName((Objects.isNull(nameConstrainForm.getLastname()) ? "" : nameConstrainForm.getLastname()) + (Objects.isNull(nameConstrainForm.getGeneration()) ? "" : nameConstrainForm.getGeneration()));
        result.setPinyin("");
        result.setMeaning("");
        result.setSource("");
        result.setNamelibType(false);
        Integer realNeededSize = nameConstrainForm.getNameSize() - (Objects.isNull(nameConstrainForm.getGeneration()) ? 0 : nameConstrainForm.getGeneration().length());
        for (int i = 0; i < realNeededSize; i++) {
            SingleCharacterModel selectedCharacter = randomSelect(constrainedCharacters);
            result.setName(result.getName() + selectedCharacter.getCharacter());
            result.setPinyin(result.getPinyin() + (i > 0 ? " " : "") + selectedCharacter.getPinyin());
            result.setMeaning(result.getMeaning() + (i > 0 ? "\n" : "") + selectedCharacter.getMeaning());
            result.setSource(result.getSource() + (i > 0 ? "\n" : "") + selectedCharacter.getIdiom() + "\n" + selectedCharacter.getPoetry());
        }
        return result;
    }

    @Override
    public OrderGeneratedNameModel newNameFromNameLibrary(NameConstrainForm nameConstrainForm) {
        List<NameLibraryModel> constrainedNames = nameLibraryMapper.constrainedNames(nameConstrainForm);
        if (constrainedNames.size() <= 0) return null;
        NameLibraryModel selectedName = randomSelect(constrainedNames);
        OrderGeneratedNameModel result = new OrderGeneratedNameModel();
        result.setName((Objects.isNull(nameConstrainForm.getLastname()) ? "" : nameConstrainForm.getLastname()) + selectedName.getName());
        result.setMeaning(selectedName.getMeaning());
        result.setSource(selectedName.getSource());
        result.setPinyin(selectedName.getPinyin());
        result.setNamelibType(true);
        return result;
    }

    private <T> T randomSelect(List<T> constrainedCharacters) {
        Random rand = new Random(new Date().getTime());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return constrainedCharacters.get(rand.nextInt(constrainedCharacters.size()));
    }
}
