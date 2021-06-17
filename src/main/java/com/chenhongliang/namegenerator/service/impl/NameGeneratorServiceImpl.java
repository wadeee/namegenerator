package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.mapper.*;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import com.chenhongliang.namegenerator.util.RandomUtils;
import com.chenhongliang.namegenerator.util.SplitStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NameGeneratorServiceImpl implements NameGeneratorService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private SingleCharacterManageMapper singleCharacterManageMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @Autowired
    private NameLibraryManageMapper nameLibraryManageMapper;

    @Override
    public List<OrderGeneratedNameModel> newNamesFromCharacter(String orderId) {
        OrderModel orderModel = orderMapper.getDetail(orderId);
        NameConstrainForm nameConstrainForm = orderModelToNameConstrainForm(orderModel, false);

        List<OrderGeneratedNameModel> orderGeneratedNameModelList = orderMapper.getGeneratedNames(orderId, false);
        List<OrderGeneratedNameModel> addList = new ArrayList<>();
        Map<String, List<String>> wuxingToCharactersMap = new HashMap<>();
        if (Objects.isNull(nameConstrainForm.getWuxing()) || nameConstrainForm.getWuxing().isEmpty()) {
            NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
            List<String> wuxing = new ArrayList<>();
            nameConstrainFormNow.setWuxing(wuxing);
            wuxingToCharactersMap.put("", singleCharacterMapper.constrainedCharacters(nameConstrainFormNow));
        } else {
            for (String temp : nameConstrainForm.getWuxing()) {
                NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
                List<String> wuxing = new ArrayList<>();
                wuxing.add(temp);
                nameConstrainFormNow.setWuxing(wuxing);
                wuxingToCharactersMap.put(temp, singleCharacterMapper.constrainedCharacters(nameConstrainFormNow));
            }
        }
        int loopCnt = 0;
        while (addList.size() < 20) {
            String generatedName = newNameFromCharacter(nameConstrainForm, wuxingToCharactersMap);
            if (Objects.isNull(generatedName)) {
                if (loopCnt > 10) break;
                loopCnt++;
                continue;
            }
            loopCnt = 0;
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                if (generatedName.equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                OrderGeneratedNameModel generatedNameModel = getNameInfoFromCharacter(generatedName, orderModel.getGeneration());
                generatedNameModel.setOrderId(Integer.parseInt(orderId));
                orderGeneratedNameModelList.add(generatedNameModel);
                addList.add(generatedNameModel);
            }
        }
        for (OrderGeneratedNameModel temp : addList) {
            orderMapper.addGeneratedName(temp);
        }
        return orderGeneratedNameModelList;

    }

    @Override
    public List<OrderGeneratedNameModel> newNamesFromNameLibrary(String orderId) {
        OrderModel orderModel = orderMapper.getDetail(orderId);
        NameConstrainForm nameConstrainForm = orderModelToNameConstrainForm(orderModel, true);

        List<OrderGeneratedNameModel> orderGeneratedNameModelList = orderMapper.getGeneratedNames(orderId, true);
        List<OrderGeneratedNameModel> addList = new ArrayList<>();
        List<String> namesList = new ArrayList<>();
        for (Integer temp : nameConstrainForm.getNameSize()) {
            if (temp.equals(1)) {
                for (String wx : nameConstrainForm.getWuxing()) {
                    List<Integer> nsl = new ArrayList<>();
                    nsl.add(temp);
                    List<String> wxl = new ArrayList<>();
                    wxl.add(wx);
                    NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
                    nameConstrainFormNow.setWuxing(wxl);
                    nameConstrainFormNow.setNameSize(nsl);
                    namesList.addAll(nameLibraryMapper.constrainedNames(nameConstrainFormNow));
                }
            } else {
                List<Integer> nsl = new ArrayList<>();
                nsl.add(temp);
                NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
                nameConstrainFormNow.setNameSize(nsl);
                namesList.addAll(nameLibraryMapper.constrainedNames(nameConstrainFormNow));
            }
        }
        Collections.shuffle(namesList);
        for (String generatedName : namesList) {
            if (addList.size() >= 20) break;
            if (Objects.isNull(generatedName)) continue;
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                if (generatedName.equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                OrderGeneratedNameModel generatedNameModel = getNameInfoFromNameLibrary(generatedName);
                generatedNameModel.setOrderId(Integer.parseInt(orderId));
                generatedNameModel.setName(generatedNameModel.getName());
                orderGeneratedNameModelList.add(generatedNameModel);
                addList.add(generatedNameModel);
            }
        }
        if (addList.size() < 20) {
            namesList = new ArrayList<>();
            List<String> wuxingList = new ArrayList<>();
            for (String wuxing : nameConstrainForm.getWuxing()) {
                wuxingList.add(wuxing);
                wuxingList.add(wuxing + " " + wuxing);
                wuxingList.add(wuxing + " " + wuxing + " " + wuxing);
            }
            NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
            nameConstrainFormNow.setWuxing(wuxingList);
            namesList.addAll(nameLibraryMapper.constrainedNamesForCorrectWuxing(nameConstrainFormNow));
            Collections.shuffle(namesList);
            for (String generatedName : namesList) {
                if (addList.size() >= 20) break;
                if (Objects.isNull(generatedName)) continue;
                Boolean flag = true;
                for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                    if (generatedName.equals(temp.getName())) {
                        flag = false;
                    }
                }
                if (flag) {
                    OrderGeneratedNameModel generatedNameModel = getNameInfoFromNameLibrary(generatedName);
                    generatedNameModel.setOrderId(Integer.parseInt(orderId));
                    generatedNameModel.setName(generatedNameModel.getName());
                    orderGeneratedNameModelList.add(generatedNameModel);
                    addList.add(generatedNameModel);
                }
            }
        }
        if (addList.size() < 20) {
            namesList = new ArrayList<>();
            for (Integer temp : nameConstrainForm.getNameSize()) {
                if (!temp.equals(1)) {
                    for (String wx : nameConstrainForm.getWuxing()) {
                        List<Integer> nsl = new ArrayList<>();
                        nsl.add(temp);
                        List<String> wxl = new ArrayList<>();
                        wxl.add(wx);
                        NameConstrainForm nameConstrainFormNow = new NameConstrainForm(nameConstrainForm);
                        nameConstrainFormNow.setNameSize(nsl);
                        nameConstrainFormNow.setWuxing(wxl);
                        namesList.addAll(nameLibraryMapper.constrainedNames(nameConstrainFormNow));
                    }
                }
            }
            Collections.shuffle(namesList);
            for (String generatedName : namesList) {
                if (addList.size() >= 20) break;
                if (Objects.isNull(generatedName)) continue;
                Boolean flag = true;
                for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                    if (generatedName.equals(temp.getName())) {
                        flag = false;
                    }
                }
                if (flag) {
                    OrderGeneratedNameModel generatedNameModel = getNameInfoFromNameLibrary(generatedName);
                    generatedNameModel.setOrderId(Integer.parseInt(orderId));
                    generatedNameModel.setName(generatedNameModel.getName());
                    orderGeneratedNameModelList.add(generatedNameModel);
                    addList.add(generatedNameModel);
                }
            }
        }
        for (OrderGeneratedNameModel temp : addList) {
            orderMapper.addGeneratedName(temp);
        }
        return orderGeneratedNameModelList;
    }

    @Override
    public Boolean removeGeneratedNames(String orderId) {
        return orderMapper.clearGeneratedNames(orderId);
    }

    private String newNameFromCharacter(NameConstrainForm nameConstrainForm, Map<String, List<String>> wuxingToCharactersMap) {
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
    public OrderGeneratedNameModel getNameInfoFromCharacter(String name, String generation) {
        OrderGeneratedNameModel orderGeneratedNameModel = new OrderGeneratedNameModel();
        if (!Objects.isNull(generation) && !generation.isEmpty()) {
            orderGeneratedNameModel.setName(RandomUtils.randomInsert(name, generation));
        } else {
            orderGeneratedNameModel.setName(name);
        }
        orderGeneratedNameModel.setPinyin("");
        orderGeneratedNameModel.setWuxing("");
        orderGeneratedNameModel.setMeaning("");
        orderGeneratedNameModel.setSource("");
        orderGeneratedNameModel.setNamelibType(false);
        for (int i = 0; i < name.length(); i++) {
            SingleCharacterModel singleCharacterModel = singleCharacterManageMapper.selectByCharacter(name.substring(i, i + 1));
            if (i == 0) {
                orderGeneratedNameModel.setPinyin(singleCharacterModel.getPinyin());
                orderGeneratedNameModel.setWuxing(singleCharacterModel.getWuxing());
                orderGeneratedNameModel.setMeaning(singleCharacterModel.getMeaning());
                orderGeneratedNameModel.setSource(singleCharacterModel.getIdiom() + "\n" + singleCharacterModel.getPoetry());
            } else {
                orderGeneratedNameModel.setPinyin(orderGeneratedNameModel.getPinyin() + " " + singleCharacterModel.getPinyin());
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
        if (wuxing.size() > 1 && resultSet.size() <= 1) return disorder(wuxing, nameSize);
        return result;
    }

    private NameConstrainForm orderModelToNameConstrainForm(OrderModel orderModel, Boolean isNameLibrary) {
        NameConstrainForm nameConstrainForm = new NameConstrainForm();
        nameConstrainForm.setLastname(orderModel.getLastname());
        nameConstrainForm.setSex(orderModel.getSex());
        List<Integer> nameSizeList = new ArrayList<>();
        Integer generationCounter = isNameLibrary ? 0 : (Objects.isNull(orderModel.getGeneration()) ? 0 : orderModel.getGeneration().length());
        if (orderModel.getNameSize().contains("二字名")) {
            nameSizeList.add(1 - generationCounter);
        }
        if (orderModel.getNameSize().contains("三字名")) {
            nameSizeList.add(2 - generationCounter);
        }
        if (orderModel.getNameSize().contains("四字名")) {
            nameSizeList.add(3 - generationCounter);
        }
        nameConstrainForm.setNameSize(nameSizeList);
        nameConstrainForm.setGeneration(orderModel.getGeneration());
        if (!Objects.isNull(orderModel.getWuxing()) && !orderModel.getWuxing().isEmpty()) {
            nameConstrainForm.setWuxing(Arrays.asList(orderModel.getWuxing().split(" ")));
        } else {
            nameConstrainForm.setWuxing(new ArrayList<>());
        }
        nameConstrainForm.setBannedCharacter(SplitStringUtils.splitString(orderModel.getBannedCharacter()));
        nameConstrainForm.setBannedPinyin(SplitStringUtils.splitString(orderModel.getBannedPinyin()));
        return nameConstrainForm;
    }

}
