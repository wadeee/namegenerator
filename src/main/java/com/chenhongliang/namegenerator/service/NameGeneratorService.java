package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;

import java.util.List;
import java.util.Map;

public interface NameGeneratorService {

    List<OrderGeneratedNameModel> newNamesFromCharacter(String orderId);

    List<OrderGeneratedNameModel> newNamesFromNameLibrary(String orderId);

    Boolean removeGeneratedNames(String orderId);

    String newNameFromCharacter(NameConstrainForm nameConstrainForm, Map<String, List<String>> wuxingToCharactersMap);

    OrderGeneratedNameModel getNameInfoFromCharacter(String name);

    OrderGeneratedNameModel getNameInfoFromNameLibrary(String name);
}
