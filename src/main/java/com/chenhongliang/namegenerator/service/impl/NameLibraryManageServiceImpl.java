package com.chenhongliang.namegenerator.service.impl;

import com.chenhongliang.namegenerator.exception.NoCharacterException;
import com.chenhongliang.namegenerator.form.NameLibraryManageForm;
import com.chenhongliang.namegenerator.mapper.NameLibraryManageMapper;
import com.chenhongliang.namegenerator.mapper.NameLibraryMapper;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import com.chenhongliang.namegenerator.service.NameLibraryManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameLibraryManageServiceImpl implements NameLibraryManageService {

    @Autowired
    private NameLibraryManageMapper nameLibraryManageMapper;

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @Override
    public Integer getNameAmount() {
        return nameLibraryManageMapper.allAmount();
    }

    @Override
    public NameLibraryManageForm getNameInfo(String name) {
        if (!nameLibraryMapper.isExist(name)) {
            throw new NoCharacterException();
        }
        NameLibraryModel result = nameLibraryManageMapper.selectByName(name);
        NameLibraryManageForm nameLibraryManageForm = new NameLibraryManageForm();
        nameLibraryManageForm.setName(result.getName());
        nameLibraryManageForm.setMeaning(result.getMeaning());
        nameLibraryManageForm.setWuxing(result.getWuxing());
        nameLibraryManageForm.setSource(result.getSource());
        nameLibraryManageForm.setPinyin(result.getPinyin());
        nameLibraryManageForm.setMale(result.getMale());
        nameLibraryManageForm.setFemale(result.getFemale());
        return nameLibraryManageForm;
    }

    @Override
    public Integer updateNameInfo(NameLibraryManageForm nameLibraryManageForm) {

        return nameLibraryManageMapper.updateName(nameLibraryManageForm);
    }

    @Override
    public Integer deleteNameInfo(String name) {
        return nameLibraryManageMapper.deleteName(name);
    }
}
