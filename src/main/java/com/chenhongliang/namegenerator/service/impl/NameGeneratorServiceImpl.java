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

    static NameConstrainForm preCharacterForm = new NameConstrainForm();

    static NameConstrainForm preNameLibraryForm = new NameConstrainForm();

    static List<SingleCharacterModel> constrainedCharacters = new ArrayList<>();

    static List<NameLibraryModel> constrainedNames = new ArrayList<>();

    static long preRandSeed = new Date().getTime();

    @Override
    public String newNameFromCharacter(NameConstrainForm nameConstrainForm) {
        Integer nameSize = randomSelect(nameConstrainForm.getNameSize());
        List<String> wuxingOrder, wuxing1, wuxing2, wuxing3;
        switch (nameSize) {
            case 1:
                wuxing1 = new ArrayList<>();
                wuxing1.add(randomSelect(nameConstrainForm.getWuxing()));
                return randomCharacterWithWuxing(nameConstrainForm, wuxing1);
            case 2:
                wuxingOrder = disorder(nameConstrainForm.getWuxing(), nameSize);
                wuxing1 = new ArrayList<>();
                wuxing1.add(wuxingOrder.get(0));
                wuxing2 = new ArrayList<>();
                wuxing2.add(wuxingOrder.get(1));
                return randomCharacterWithWuxing(nameConstrainForm, wuxing1) + randomCharacterWithWuxing(nameConstrainForm, wuxing2);
            case 3:
                wuxingOrder = disorder(nameConstrainForm.getWuxing(), nameSize);
                wuxing1 = new ArrayList<>();
                wuxing1.add(wuxingOrder.get(0));
                wuxing2 = new ArrayList<>();
                wuxing2.add(wuxingOrder.get(1));
                wuxing3 = new ArrayList<>();
                wuxing3.add(wuxingOrder.get(2));
                return randomCharacterWithWuxing(nameConstrainForm, wuxing1) + randomCharacterWithWuxing(nameConstrainForm, wuxing2) + randomCharacterWithWuxing(nameConstrainForm, wuxing3);
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
    public String newNameFromNameLibrary(NameConstrainForm nameConstrainForm) {
        Integer nameSize = randomSelect(nameConstrainForm.getNameSize());
        List<String> wuxing;
        List<Integer> nameSizeList;
        switch (nameSize) {
            case 1:
                wuxing = new ArrayList<>();
                wuxing.add(randomSelect(nameConstrainForm.getWuxing()));
                nameSizeList = new ArrayList<>();
                nameSizeList.add(1);
                return randomNameLibraryWithWuxing(nameConstrainForm, wuxing, nameSizeList);
            case 2:
                nameSizeList = new ArrayList<>();
                nameSizeList.add(2);
                return randomNameLibraryWithWuxing(nameConstrainForm, nameConstrainForm.getWuxing(), nameSizeList);
            case 3:
                nameSizeList = new ArrayList<>();
                nameSizeList.add(3);
                return randomNameLibraryWithWuxing(nameConstrainForm, nameConstrainForm.getWuxing(), nameSizeList);
        }
        return null;
    }

    @Override
    public OrderGeneratedNameModel getNameInfoFromNameLibrary(String name) {
        NameLibraryModel nameLibraryModel = nameLibraryManageMapper.selectByName(name);
        OrderGeneratedNameModel orderGeneratedNameModel = new OrderGeneratedNameModel();
        orderGeneratedNameModel.setName(name);
        orderGeneratedNameModel.setNamelibType(true);
        System.out.println(name);
        orderGeneratedNameModel.setPinyin(nameLibraryModel.getPinyin());
        orderGeneratedNameModel.setWuxing(nameLibraryModel.getWuxing());
        orderGeneratedNameModel.setMeaning(nameLibraryModel.getMeaning());
        orderGeneratedNameModel.setSource(nameLibraryModel.getSource());
        return orderGeneratedNameModel;
    }

    private <T> T randomSelect(List<T> constrainedCharacters) {
        if (constrainedCharacters.size()<=0) return null;
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
        NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
        nameConstrainFormNow.setWuxing(wuxing);
        System.out.println(nameConstrainForm.toString());
        return randomSelect(singleCharacterMapper.constrainedCharacters(nameConstrainFormNow));
    }

    private String randomNameLibraryWithWuxing(NameConstrainForm nameConstrainForm, List<String> wuxing, List<Integer> nameSizeList) {
        NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
        nameConstrainFormNow.setWuxing(wuxing);
        nameConstrainFormNow.setNameSize(nameSizeList);
        System.out.println(nameConstrainFormNow.toString());
        return randomSelect(nameLibraryMapper.constrainedNames(nameConstrainFormNow));
    }


    private List<String> disorder(List<String> wuxing, Integer nameSize) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nameSize; i++) {
            result.add(randomSelect(wuxing));
        }
        Set<String> resultSet = new HashSet<>(result);
        if (resultSet.size()<=1) return disorder(wuxing, nameSize);
        return result;
    }
}
