package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;

public interface NameGeneratorService {

//    OrderGeneratedNameModel newNameFromCharacter(NameConstrainForm nameConstrainForm);

    String newNameFromCharacter(NameConstrainForm nameConstrainForm);

    OrderGeneratedNameModel getNameInfoFromCharacter(String name);

//    OrderGeneratedNameModel newNameFromNameLibrary(NameConstrainForm nameConstrainForm);
    String newNameFromNameLibrary(NameConstrainForm nameConstrainForm);

    OrderGeneratedNameModel getNameInfoFromNameLibrary(String name);
}
