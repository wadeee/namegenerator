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
    public String newNameFromCharacter(NameConstrainForm nameConstrainForm) {
        Integer nameSize = randomSelect(nameConstrainForm.getNameSize());
        List<String> wuxing;
        switch (nameSize) {
            case 1:
                wuxing = new ArrayList<>();
                wuxing.add(randomSelect(nameConstrainForm.getWuxing()));
                return randomCharacterWithWuxing(nameConstrainForm, wuxing);
            case 2:
                wuxing = new ArrayList<>();
                wuxing.add(randomSelect(nameConstrainForm.getWuxing()));

        }
        return null;
    }

    @Override
    public OrderGeneratedNameModel getNameInfoFromCharacter(String name) {
        return null;
    }

    @Override
    public String newNameFromNameLibrary(NameConstrainForm nameConstrainForm) {
        return null;
    }

    @Override
    public OrderGeneratedNameModel getNameInfoFromNameLibrary(String name) {
        return null;
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

    private String randomCharacterWithWuxing(NameConstrainForm nameConstrainForm, List<String> wuxing) {
        wuxing.add(randomSelect(nameConstrainForm.getWuxing()));
        NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
        nameConstrainFormNow.setWuxing(wuxing);
        return randomSelect(singleCharacterMapper.constrainedCharacters(nameConstrainFormNow));
    }
}
