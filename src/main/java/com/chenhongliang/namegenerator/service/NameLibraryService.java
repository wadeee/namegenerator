package com.chenhongliang.namegenerator.service;

import com.chenhongliang.namegenerator.form.NameLibraryForm;
import com.chenhongliang.namegenerator.form.NameLibraryPinyinForm;
import com.chenhongliang.namegenerator.vo.AddNameLibraryResultVo;

public interface NameLibraryService {

    AddNameLibraryResultVo addName(NameLibraryForm nameLibraryForm);

    Boolean updatePinyin(NameLibraryPinyinForm nameLibraryPinyinForm);

}
