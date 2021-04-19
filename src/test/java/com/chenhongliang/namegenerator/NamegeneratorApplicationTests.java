package com.chenhongliang.namegenerator;

import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.service.QAService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamegeneratorApplicationTests {

    @Autowired
    private ChineseSearchService chineseSearchService;

    @Autowired
    private QAService qaService;

//    @Autowired
//    private SingleCharacterService singleCharacterService;
//
//    @Autowired
//    private SingleCharacterMapper singleCharacterMapper;

    @Test
    void contextLoads() throws Exception {
//        String str = "a, 她,c,你 ,我";
//        List<String> ls = Arrays.asList(str.split("\\s*,\\s*"));
//        System.out.println(ls.toString());
//        System.out.println(chineseSearchService.search("秦的诗词").toString());
//        SingleCharacterVo singleCharacterVo = new SingleCharacterVo("你  , 我  ,  他", "好");
//        singleCharacterService.addCharacters(singleCharacterVo);
//        System.out.println(singleCharacterMapper.isExist("你"));
//        System.out.println(singleCharacterMapper.isExist("好"));

//        JSONObject json = JSON.parseObject("{\"result\":[],\"log_id\":2016232440733366355}");
//        System.out.println(json.toString());
//        String poetry = "";
//        try {
////            singleCharacterModel.setPoetry(json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer").toString());
//            json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer").toString();
//            poetry = json.getJSONArray("result").getJSONObject(0).getJSONObject("response").getJSONArray("answer").toString();
//        } catch (Exception e) {
//        }
//        System.out.println("poetry: " + poetry);
//        System.out.println(chineseSearchService.search("呃的诗词").toString());
//        JSONObject json = qaService.search("呃的诗词");
//        System.out.println(json.toString());
//        String str = "你，　我, 他  　 　 ， 　  你";
//        System.out.println(Arrays.asList(str.split("(　|\\s)*(,|，)(　|\\s)*")).toString());
        System.out.println("".length());
    }

}
