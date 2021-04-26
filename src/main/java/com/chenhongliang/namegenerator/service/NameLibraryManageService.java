package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.NameLibraryManageForm;

public interface NameLibraryManageService {

    Integer getNameAmount();

    NameLibraryManageForm getNameInfo(String name);

    Integer updateNameInfo(NameLibraryManageForm nameLibraryManageForm);

    Integer deleteNameInfo(String character);
}
