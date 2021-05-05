package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.model.OrderGeneratedNameModel;

public interface NameGeneratorService {

    OrderGeneratedNameModel newNameFromCharacter(NameConstrainForm nameConstrainForm);

    OrderGeneratedNameModel newNameFromNameLibrary(NameConstrainForm nameConstrainForm);
}
