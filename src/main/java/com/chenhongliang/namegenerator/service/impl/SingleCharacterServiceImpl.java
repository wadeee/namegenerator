package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import com.chenhongliang.namegenerator.vo.SingleCharacterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SingleCharacterServiceImpl implements SingleCharacterService {

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Autowired
    private ChineseSearchService chineseSearchService;

    @Override
    public List<SingleCharacterModel> findAll() {
        return singleCharacterMapper.findAll();
    }

    @Override
    public String addCharacters(SingleCharacterVo singleCharacterVo) throws Exception {
        List<String> boyCharacters = splitString(singleCharacterVo.getBoyCharacters());
        List<String> girlCharacters = splitString(singleCharacterVo.getGirlCharacters());
        for (String character : boyCharacters) {
            if (character.length() == 1 && !singleCharacterMapper.isExist(character)) {
                singleCharacterMapper.insert(getInfoFromApi(character));
            }
        }
        for (String character : girlCharacters) {
            if (character.length() == 1 && !singleCharacterMapper.isExist(character)) {
                singleCharacterMapper.insert(getInfoFromApi(character));
            }
        }
        singleCharacterMapper.updateSex(boyCharacters, "boy");
        singleCharacterMapper.updateSex(girlCharacters, "girl");
        return "success";
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
                    singleCharacterModel.setPinyin(jsonItem.getJSONArray("objects").getJSONObject(0).getString("value"));
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
                singleCharacterModel.setIdiom(answer.toString());
            }
        } catch (Exception e) {
        }


        json = chineseSearchService.search(character + "的诗词");
        try {
            JSONArray answer = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer");
            if (!answer.get(0).toString().equals("诗词")) {
                singleCharacterModel.setPoetry(answer.toString());
            }
        } catch (Exception e) {
        }

        return singleCharacterModel;
    }
}
