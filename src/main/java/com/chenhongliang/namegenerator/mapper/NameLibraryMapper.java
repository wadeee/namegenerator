package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NameLibraryMapper {
    Boolean isExist(String name);

    Boolean isEverExist(String name);

    Integer insert(NameLibraryModel nameLibraryModel);

    Integer update(NameLibraryModel nameLibraryModel);

    Integer updateDelFlag(String name, Boolean delFlag);

    Integer updateSex(String name, Boolean male, Boolean female);

    Integer updatePinyin(String name, String pinyin, String atonalPinyin);

    List<NameLibraryModel> constrainedNames(NameConstrainForm nameConstrainForm);
}