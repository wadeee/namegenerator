package com.chenhongliang.namegenerator;

import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.service.SingleCharacterService;
import com.chenhongliang.namegenerator.vo.SingleCharacterVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamegeneratorApplicationTests {

    @Autowired
    private ChineseSearchService chineseSearchService;

    @Autowired
    private SingleCharacterService singleCharacterService;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Test
    void contextLoads() throws Exception {
//        String str = "a, 她,c,你 ,我";
//        List<String> ls = Arrays.asList(str.split("\\s*,\\s*"));
//        System.out.println(ls.toString());
//        System.out.println(chineseSearchService.search("秦的诗词").toString());
        SingleCharacterVo singleCharacterVo = new SingleCharacterVo("你  , 我  ,  他", "好");
        singleCharacterService.addCharacters(singleCharacterVo);
//        System.out.println(singleCharacterMapper.isExist("你"));
//        System.out.println(singleCharacterMapper.isExist("好"));
    }

}
