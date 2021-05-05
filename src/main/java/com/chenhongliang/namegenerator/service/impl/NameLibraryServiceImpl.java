package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenhongliang.namegenerator.form.NameLibraryForm;
import com.chenhongliang.namegenerator.form.NameLibraryPinyinForm;
import com.chenhongliang.namegenerator.mapper.NameLibraryMapper;
import com.chenhongliang.namegenerator.model.NameLibraryModel;
import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.service.NameLibraryService;
import com.chenhongliang.namegenerator.util.PinyinUtils;
import com.chenhongliang.namegenerator.vo.AddNameLibraryResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NameLibraryServiceImpl implements NameLibraryService {

    @Autowired
    private NameLibraryMapper nameLibraryMapper;

    @Autowired
    private ChineseSearchService chineseSearchService;

    @Override
    public AddNameLibraryResultVo addName(NameLibraryForm nameLibraryForm) {
        NameLibraryModel nameLibraryModel = new NameLibraryModel();
        String name = nameLibraryForm.getName();
        nameLibraryModel.setName(name);
        nameLibraryModel.setMeaning(nameLibraryForm.getMeaning());
        nameLibraryModel.setSource(nameLibraryForm.getSource());
        nameLibraryModel.setMale(nameLibraryForm.getMale());
        nameLibraryModel.setFemale(nameLibraryForm.getFemale());

        AddNameLibraryResultVo addNameLibraryResultVo = new AddNameLibraryResultVo();
        List<String> wuxings = new LinkedList<>();
        List<String> pinyins = new LinkedList<>();
        Map<String, List<String>> pinyinSelectMap = new LinkedHashMap<>();
        for (int i = 0; i < name.length(); i++) {
            try {
                getInfoFromApi(name.charAt(i), wuxings, pinyins, pinyinSelectMap);
            } catch (Exception e) {
            }
        }
        nameLibraryModel.setPinyin(String.join(" ", pinyins));
        nameLibraryModel.setWuxing(String.join(" ", wuxings));

        if (nameLibraryMapper.isExist(name)) {
            nameLibraryMapper.update(nameLibraryModel);
        } else {
            nameLibraryMapper.insert(nameLibraryModel);
        }

        addNameLibraryResultVo.setPinyinSelectMap(pinyinSelectMap);
        return addNameLibraryResultVo;
    }

    @Override
    public Boolean updatePinyin(NameLibraryPinyinForm nameLibraryPinyinForm) {
        nameLibraryMapper.updatePinyin(nameLibraryPinyinForm.getName(), nameLibraryPinyinForm.getPinyin(), PinyinUtils.atonalPinyin(nameLibraryPinyinForm.getPinyin()));
        return true;
    }

    private void getInfoFromApi(char character, List<String> wuxings, List<String> pinyins, Map<String, List<String>> pinyinSelectMap) throws Exception {
        JSONObject json = chineseSearchService.search(character + "的五行");
        try {
            wuxings.add(json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer").getString(0));
            JSONArray attrs = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("entity").getJSONObject(0).getJSONArray("attrs");
            for (Object item : attrs) {
                JSONObject jsonItem = (JSONObject) item;
                String label = jsonItem.getString("label");
                if (label.equals("拼音")) {
                    String pinyin = jsonItem.getJSONArray("objects").getJSONObject(0).getString("value");
                    pinyin = pinyin.substring(1, pinyin.length() - 1);
                    pinyins.add(pinyin);
                    List<String> pinyinList = Arrays.asList(pinyin.split("(　|\\s)*(,|，)(　|\\s)*"));
                    pinyinSelectMap.put(Character.toString(character), pinyinList);
                }
            }
        } catch (Exception e) {
        }
    }
}
