package com.chenhongliang.namegenerator.controller;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.mapper.NameLibraryMapper;
import com.chenhongliang.namegenerator.mapper.OrderMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;
import com.chenhongliang.namegenerator.model.OrderModel;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import com.chenhongliang.namegenerator.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/name-generator")
public class NameGeneratorController {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @GetMapping("/characters/{orderId}")
    @ResponseBody
    public List<OrderGeneratedNameModel> fromCharacters(@PathVariable("orderId") String orderId) {
        OrderModel orderModel = orderMapper.getDetail(orderId);
        NameConstrainForm nameConstrainForm = orderModelToNameConstrainForm(orderModel);

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
        while (addList.size() < 20) {
            String generatedName = nameGeneratorService.newNameFromCharacter(nameConstrainForm, wuxingToCharactersMap);
            if (Objects.isNull(generatedName)) continue;
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                if (generatedName.equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                OrderGeneratedNameModel generatedNameModel = nameGeneratorService.getNameInfoFromCharacter(generatedName);
                generatedNameModel.setOrderId(Integer.parseInt(orderId));
                generatedNameModel.setName(orderModel.getLastname() + generatedNameModel.getName());
                orderGeneratedNameModelList.add(generatedNameModel);
                addList.add(generatedNameModel);
            }
        }
        for (OrderGeneratedNameModel temp : addList) {
            orderMapper.addGeneratedName(temp);
        }
        return orderGeneratedNameModelList;
    }

    @GetMapping("/name-library/{orderId}")
    @ResponseBody
    public List<OrderGeneratedNameModel> fromNameLibrary(@PathVariable("orderId") String orderId) {
        OrderModel orderModel = orderMapper.getDetail(orderId);
        NameConstrainForm nameConstrainForm = orderModelToNameConstrainForm(orderModel);

        List<OrderGeneratedNameModel> orderGeneratedNameModelList = orderMapper.getGeneratedNames(orderId, true);
        List<OrderGeneratedNameModel> addList = new ArrayList<>();
        List<String> namesList = new ArrayList<>();
        for (Integer temp: nameConstrainForm.getNameSize()) {
            if (temp.equals(1)) {
                for (String wx: nameConstrainForm.getWuxing()) {
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
        if (namesList.size()<20) {
            for (String generatedName: namesList) {
                Boolean flag = true;
                for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                    if (generatedName.equals(temp.getName())) {
                        flag = false;
                    }
                }
                if (flag) {
                    OrderGeneratedNameModel generatedNameModel = nameGeneratorService.getNameInfoFromNameLibrary(generatedName);
                    generatedNameModel.setOrderId(Integer.parseInt(orderId));
                    generatedNameModel.setName(orderModel.getLastname() + generatedNameModel.getName());
                    orderGeneratedNameModelList.add(generatedNameModel);
                    addList.add(generatedNameModel);
                }
            }
            namesList = new ArrayList<>();
            for (Integer temp: nameConstrainForm.getNameSize()) {
                if (!temp.equals(1)) {
                    for (String wx: nameConstrainForm.getWuxing()) {
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
        }
        while (addList.size() < 20) {
            String generatedName = RandomUtils.randomSelect(namesList);
            if (Objects.isNull(generatedName)) continue;
            Boolean flag = true;
            for (OrderGeneratedNameModel temp : orderGeneratedNameModelList) {
                if (generatedName.equals(temp.getName())) {
                    flag = false;
                }
            }
            if (flag) {
                OrderGeneratedNameModel generatedNameModel = nameGeneratorService.getNameInfoFromNameLibrary(generatedName);
                generatedNameModel.setOrderId(Integer.parseInt(orderId));
                generatedNameModel.setName(orderModel.getLastname() + generatedNameModel.getName());
                orderGeneratedNameModelList.add(generatedNameModel);
                addList.add(generatedNameModel);
            }
        }
        for (OrderGeneratedNameModel temp : addList) {
            orderMapper.addGeneratedName(temp);
        }
        return orderGeneratedNameModelList;
    }

    private List<String> splitString(String str) {
        if (Objects.isNull(str)) return new ArrayList<>();
        return Arrays.asList(str.split("(　|\\s)*(,|，|　|\\s)(　|\\s)*"));
    }

    private NameConstrainForm orderModelToNameConstrainForm(OrderModel orderModel) {
        NameConstrainForm nameConstrainForm = new NameConstrainForm();
        nameConstrainForm.setLastname(orderModel.getLastname());
        nameConstrainForm.setSex(orderModel.getSex());
        List<Integer> nameSizeList = new ArrayList<>();
        if (orderModel.getNameSize().contains("二字名")) {
            nameSizeList.add(1);
        }
        if (orderModel.getNameSize().contains("三字名")) {
            nameSizeList.add(2);
        }
        if (orderModel.getNameSize().contains("四字名")) {
            nameSizeList.add(3);
        }
        nameConstrainForm.setNameSize(nameSizeList);
        nameConstrainForm.setGeneration(orderModel.getGeneration());
        if (!Objects.isNull(orderModel.getWuxing())) {
            nameConstrainForm.setWuxing(Arrays.asList(orderModel.getWuxing().split(" ")));
        }
        nameConstrainForm.setBannedCharacter(splitString(orderModel.getBannedCharacter()));
        nameConstrainForm.setBannedPinyin(splitString(orderModel.getBannedPinyin()));
        return nameConstrainForm;
    }

}
