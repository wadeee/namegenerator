package com.chenhongliang.namegenerator.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static Map<Character, Character> pinyinSwitchMap = generatePinyinSwitchMap();

    private static Map<Character, Character> generatePinyinSwitchMap() {
        Map<Character, Character> result = new HashMap<>();
        result.put('ā', 'a');
        result.put('á', 'a');
        result.put('ǎ', 'a');
        result.put('à', 'a');
        result.put('ō', 'o');
        result.put('ó', 'o');
        result.put('ǒ', 'o');
        result.put('ò', 'o');
        result.put('ē', 'e');
        result.put('é', 'e');
        result.put('ě', 'e');
        result.put('è', 'e');
        result.put('ī', 'i');
        result.put('í', 'i');
        result.put('ǐ', 'i');
        result.put('ì', 'i');
        result.put('ū', 'u');
        result.put('ú', 'u');
        result.put('ǔ', 'u');
        result.put('ù', 'u');
        result.put('ǖ', 'ü');
        result.put('ǘ', 'ü');
        result.put('ǚ', 'ü');
        result.put('ǜ', 'ü');
        return result;
    }

    public static List<String> wuxings = Arrays.asList(new String[]{"金", "木", "水", "火", "土"});
}
