package com.chenhongliang.namegenerator.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public final static Map<Character, Character> pinyinSwitchMap = new HashMap<>();

    static {
        pinyinSwitchMap.put('ā', 'a');
        pinyinSwitchMap.put('á', 'a');
        pinyinSwitchMap.put('ǎ', 'a');
        pinyinSwitchMap.put('à', 'a');
        pinyinSwitchMap.put('ō', 'o');
        pinyinSwitchMap.put('ó', 'o');
        pinyinSwitchMap.put('ǒ', 'o');
        pinyinSwitchMap.put('ò', 'o');
        pinyinSwitchMap.put('ē', 'e');
        pinyinSwitchMap.put('é', 'e');
        pinyinSwitchMap.put('ě', 'e');
        pinyinSwitchMap.put('è', 'e');
        pinyinSwitchMap.put('ī', 'i');
        pinyinSwitchMap.put('í', 'i');
        pinyinSwitchMap.put('ǐ', 'i');
        pinyinSwitchMap.put('ì', 'i');
        pinyinSwitchMap.put('ū', 'u');
        pinyinSwitchMap.put('ú', 'u');
        pinyinSwitchMap.put('ǔ', 'u');
        pinyinSwitchMap.put('ù', 'u');
        pinyinSwitchMap.put('ǖ', 'ü');
        pinyinSwitchMap.put('ǘ', 'ü');
        pinyinSwitchMap.put('ǚ', 'ü');
        pinyinSwitchMap.put('ǜ', 'ü');
    }

    public final static List<String> wuxings = Arrays.asList("金", "木", "水", "火", "土");

    public final static Map<String, String> hongyanTranslateMap = new HashMap<>();
    
    static {
        hongyanTranslateMap.put("ZhuXing", "主星");
        hongyanTranslateMap.put("TianGan", "天干");
        hongyanTranslateMap.put("DiZhi", "地支");
        hongyanTranslateMap.put("CangGan", "藏干");
        hongyanTranslateMap.put("FuXing", "副星");
        hongyanTranslateMap.put("XingYun", "十二运");
        hongyanTranslateMap.put("KongWan", "KongWan");
        hongyanTranslateMap.put("ShenSha", "神煞");
        hongyanTranslateMap.put("KongWang", "空亡");
        hongyanTranslateMap.put("MingGong", "命宫");
        hongyanTranslateMap.put("TaiYuan", "胎元");
        hongyanTranslateMap.put("TaiXi", "胎息");
        hongyanTranslateMap.put("JiaoYunShiJian", "叫运时间");
        hongyanTranslateMap.put("RiYuan", "日元");
        hongyanTranslateMap.put("WuXing", "五行");
        hongyanTranslateMap.put("QiangRuo", "强弱");
        hongyanTranslateMap.put("QiangRuoShu", "强弱数");
        hongyanTranslateMap.put("XingXiang", "星象");
        hongyanTranslateMap.put("GeJu", "格局");
        hongyanTranslateMap.put("YongShen", "用神");
        hongyanTranslateMap.put("XiShen", "喜神");
        hongyanTranslateMap.put("JiShen", "忌神");
        hongyanTranslateMap.put("QingZhuo", "清浊");
        hongyanTranslateMap.put("LiuTong", "LiuTong");
        hongyanTranslateMap.put("Mu", "木");
        hongyanTranslateMap.put("Jin", "金");
        hongyanTranslateMap.put("Shui", "水");
        hongyanTranslateMap.put("Tu", "土");
        hongyanTranslateMap.put("Huo", "火");
        hongyanTranslateMap.put("TianGanHeChong", "天干合克冲");
        hongyanTranslateMap.put("DiZhiHeChong", "地支合克冲");
        hongyanTranslateMap.put("YinYan", "YinYan");
        hongyanTranslateMap.put("ZaoShi", "ZaoShi");
        hongyanTranslateMap.put("YueLing", "YueLing");
        hongyanTranslateMap.put("YongShiRenYuan", "YongShiRenYuan");
        hongyanTranslateMap.put("DaYun", "大运");
        hongyanTranslateMap.put("LsYongShen", "LsYongShen");
        hongyanTranslateMap.put("LsXiShen", "LsXiShen");
        hongyanTranslateMap.put("LsJiShen", "LsJiShen");
        hongyanTranslateMap.put("WxYongShen", "WxYongShen");
        hongyanTranslateMap.put("WxXiShen", "WxXiShen");
        hongyanTranslateMap.put("WxJiShen", "WxJiShen");
        hongyanTranslateMap.put("Bazi", "八字");
        hongyanTranslateMap.put("MingPen", "命盘");
        hongyanTranslateMap.put("XingGe", "性格");
        hongyanTranslateMap.put("XueLi", "学历");
        hongyanTranslateMap.put("CaiFu", "财富");
        hongyanTranslateMap.put("CaiFuShiYe", "财富事业");
        hongyanTranslateMap.put("DiWei", "地位");
        hongyanTranslateMap.put("LiuQin", "六亲");
        hongyanTranslateMap.put("JiBing", "疾病");
        hongyanTranslateMap.put("HunYin", "婚姻");
        hongyanTranslateMap.put("ShiYe", "事业");
        hongyanTranslateMap.put("YiJi", "宜忌");
        hongyanTranslateMap.put("XiongZai", "凶灾");
        hongyanTranslateMap.put("GuanSha", "关煞");
    }
}
