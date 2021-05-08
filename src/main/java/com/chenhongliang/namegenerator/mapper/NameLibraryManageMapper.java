package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.form.NameLibraryManageForm;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NameLibraryManageMapper {
    Integer allAmount();

    NameLibraryModel selectByName(String name);

    Integer updateName(NameLibraryManageForm nameLibraryManageForm);

    Integer deleteName(String name);

    List<String> allNames();
}