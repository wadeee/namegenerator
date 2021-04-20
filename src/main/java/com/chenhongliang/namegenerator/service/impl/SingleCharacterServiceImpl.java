package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import com.chenhongliang.namegenerator.form.SingleCharacterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SingleCharacterServiceImpl implements SingleCharacterService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private ChineseSearchService chineseSearchService;

    @Override
    public Map<String, List<String>> addCharacters(SingleCharacterForm singleCharacterForm) throws Exception {
        List<String> boyCharacters = splitString(singleCharacterForm.getBoyCharacters());
        List<String> girlCharacters = splitString(singleCharacterForm.getGirlCharacters());
        Map<String, List<String>> pinyinMap = new LinkedHashMap<>();
        analyseCharacters(boyCharacters, pinyinMap);
        analyseCharacters(girlCharacters, pinyinMap);
        singleCharacterMapper.updateSex(boyCharacters, "boy");
        singleCharacterMapper.updateSex(girlCharacters, "girl");
        return pinyinMap;
    }

    @Override
    public String updatePinyin(Map<String, String> pinyinMap) {
        for (Map.Entry<String, String> entry : pinyinMap.entrySet()) {
            singleCharacterMapper.updatePinyin(entry.getKey(), entry.getValue());
        }
        return "success";
    }

    private void analyseCharacters(List<String> boyCharacters, Map<String, List<String>> pinyinMap) throws Exception {
        for (String character : boyCharacters) {
            if (character.length() == 1 && !singleCharacterMapper.isExist(character)) {
                SingleCharacterModel singleCharacterModel = getInfoFromApi(character);
                singleCharacterMapper.insert(singleCharacterModel);
                List<String> pinyinList = Arrays.asList(singleCharacterModel.getPinyin().split("(　|\\s)*(,|，)(　|\\s)*"));
                if (pinyinList.size() > 1) {
                    pinyinMap.put(character, pinyinList);
                }
            }
        }
    }

    private List<String> splitString(String str) {
        return Arrays.asList(str.split("(　|\\s)*(,|，)(　|\\s)*"));
    }

    private SingleCharacterModel getInfoFromApi(String character) throws Exception {
        SingleCharacterModel singleCharacterModel = new SingleCharacterModel();
        singleCharacterModel.setCharacter(character);

        JSONObject json = chineseSearchService.search(character + "的五行");
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
            if (!answer.get(0).toString().equals("诗词")) {
                String answerStr = answer.toString();
                answerStr = answerStr.substring(1, answerStr.length() - 1);
                singleCharacterModel.setPoetry(answerStr);
            }
        } catch (Exception e) {
        }

        return singleCharacterModel;
    }
}
