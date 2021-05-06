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

import java.util.*;

@Service
public class NameGeneratorServiceImpl implements NameGeneratorService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    static NameConstrainForm preCharacterForm = new NameConstrainForm();

    static NameConstrainForm preNameLibraryForm = new NameConstrainForm();

    static List<SingleCharacterModel> constrainedCharacters = new ArrayList<>();

    static List<NameLibraryModel> constrainedNames = new ArrayList<>();

    static long preRandSeed = new Date().getTime();


    @Override
    public OrderGeneratedNameModel newNameFromCharacter(NameConstrainForm nameConstrainForm) {
        if (!nameConstrainForm.toString().equals(preCharacterForm.toString())) {
            constrainedCharacters = singleCharacterMapper.constrainedCharacters(nameConstrainForm);
        }
        preCharacterForm = new NameConstrainForm(nameConstrainForm);
        if (constrainedCharacters.size() <= 0) return null;
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
        if (!nameConstrainForm.toString().equals(preNameLibraryForm.toString())) {
            constrainedNames = nameLibraryMapper.constrainedNames(nameConstrainForm);
        }
        preNameLibraryForm = new NameConstrainForm(nameConstrainForm);
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
        Long randSeed = new Date().getTime();
        if (randSeed.equals(preRandSeed)) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            randSeed = new Date().getTime();
        }
        preRandSeed = randSeed;
        Random rand = new Random(randSeed);
        return constrainedCharacters.get(rand.nextInt(constrainedCharacters.size()));
    }
}
