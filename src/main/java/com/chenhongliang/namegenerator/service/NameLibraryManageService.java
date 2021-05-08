package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.NameLibraryManageForm;

import java.util.List;

public interface NameLibraryManageService {

    Integer getNameAmount();

    NameLibraryManageForm getNameInfo(String name);

    Integer updateNameInfo(NameLibraryManageForm nameLibraryManageForm);

    Integer deleteNameInfo(String character);

    List<String> allNames();
}
