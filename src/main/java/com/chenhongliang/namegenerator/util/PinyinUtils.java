package com.chenhongliang.namegenerator.util;

import com.chenhongliang.namegenerator.constant.Constant;

import java.util.Map;

public class PinyinUtils {

    public static String atonalPinyin(String pinyin) {
        String result = pinyin;
        for (Map.Entry<Character, Character> entry : Constant.pinyinSwitchMap.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
