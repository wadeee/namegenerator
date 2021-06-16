package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;

import java.util.List;

public interface NameGeneratorService {

    List<OrderGeneratedNameModel> newNamesFromCharacter(String orderId);

    List<OrderGeneratedNameModel> newNamesFromNameLibrary(String orderId);

    Boolean removeGeneratedNames(String orderId);

    OrderGeneratedNameModel getNameInfoFromCharacter(String name, String generation);

    OrderGeneratedNameModel getNameInfoFromNameLibrary(String name);
}
