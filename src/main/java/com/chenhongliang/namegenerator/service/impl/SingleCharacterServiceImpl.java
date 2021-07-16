package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenhongliang.namegenerator.exception.NotCorrectSizeException;
import com.chenhongliang.namegenerator.mapper.SingleCharacterManageMapper;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import com.chenhongliang.namegenerator.util.PinyinUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SingleCharacterServiceImpl implements SingleCharacterService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private SingleCharacterManageMapper singleCharacterManageMapper;

    @Autowired
    private ChineseSearchService chineseSearchService;

    @Override
    public SingleCharacterModel addCharacter(String character) throws Exception {
        if (character.length() != 1) {
            throw new NotCorrectSizeException();
        }
        SingleCharacterModel singleCharacterModel;
        if (singleCharacterMapper.isEverExist(character)) {
            singleCharacterMapper.updateDelFlag(character, false);
            singleCharacterModel = singleCharacterManageMapper.selectByCharacter(character);
        } else {
            singleCharacterModel = getInfoFromApi(character);
            singleCharacterModel.setFemale(false);
            singleCharacterModel.setMale(false);
            try {
                singleCharacterModel.setAtonalPinyin(PinyinUtils.getAtonalPinyin(singleCharacterModel.getPinyin()));
                singleCharacterMapper.insert(singleCharacterModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return singleCharacterModel;
    }

    @Override
    public String updatePinyin(Map<String, String> pinyinMap) {
        for (Map.Entry<String, String> entry : pinyinMap.entrySet()) {
            singleCharacterMapper.updatePinyin(entry.getKey(), entry.getValue(), PinyinUtils.getAtonalPinyin(entry.getValue()));
        }
        return "success";
    }

    private SingleCharacterModel getInfoFromApi(String character) throws Exception {
        SingleCharacterModel singleCharacterModel = new SingleCharacterModel();
        singleCharacterModel.setCharacter(character);

        JSONObject json = chineseSearchService.search(character + "的五行");
        try {
            singleCharacterModel.setWuxing(json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer").getString(0));
            JSONArray attrs = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("entity").getJSONObject(0).getJSONArray("attrs");
            for (Object item : attrs) {
                JSONObject jsonItem = (JSONObject) item;
                String label = jsonItem.getString("label");
                switch (label) {
                    case "拼音":
                        String pinyin = jsonItem.getJSONArray("objects").getJSONObject(0).getString("value");
                        pinyin = pinyin.substring(1, pinyin.length() - 1);
                        singleCharacterModel.setPinyin(pinyin);
                        break;
                    case "释义":
                        singleCharacterModel.setMeaning(jsonItem.getJSONArray("objects").getJSONObject(0).getString("value"));
                        break;
                }
            }
        } catch (Exception e) {
        }

        json = chineseSearchService.search(character + "的成语");
        try {
            JSONArray answer = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer");
            if (!answer.get(0).toString().equals("成语")) {
                String answerStr = answer.toString();
                answerStr = answerStr.substring(1, answerStr.length() - 1);
                singleCharacterModel.setIdiom(answerStr);
            }
        } catch (Exception e) {
        }


        json = chineseSearchService.search(character + "的诗词");
        try {
            JSONArray answer = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer");
            JSONArray entityList = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("entity");
            List<String> poetryList = new LinkedList<>();

            for (Object entity : entityList) {
                JSONObject jsonEntity = (JSONObject) entity;
                String author = jsonEntity.getJSONArray("attrs").getJSONObject(1).getJSONArray("objects").getJSONObject(0).getString("value");
                String poemLine = jsonEntity.getString("name");
                poetryList.add(poemLine + " —— " + author);
            }
            singleCharacterModel.setPoetry(StringUtils.join(poetryList, "\n"));
        } catch (Exception e) {
        }

        return singleCharacterModel;
    }
}
