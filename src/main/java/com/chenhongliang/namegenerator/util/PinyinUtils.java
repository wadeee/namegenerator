package com.chenhongliang.namegenerator.util;

import com.chenhongliang.namegenerator.constant.Constant;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class PinyinUtils {

    public static String getAtonalPinyin(String pinyin) {
        String result = pinyin;
        for (Map.Entry<Character, Character> entry : Constant.pinyinSwitchMap.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static String getPinyin(String str) {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        // 默认小写
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不显示拼音的声调
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder sb = new StringBuilder();
        if (!Objects.isNull(str)) {
            try {
                for (char c : str.toCharArray()) {
                    // 如果包含有中文标点除号，需要使用正则表达式
                    if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                        // if (c > 128) {
                        sb.append(Arrays.asList(PinyinHelper.toHanyuPinyinStringArray(c, outputFormat)).toString().replace("[", "").replace("]", ""));
                    } else {
                        sb.append(c);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
