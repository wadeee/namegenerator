package com.chenhongliang.namegenerator;

import com.chenhongliang.namegenerator.service.ChineseSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamegeneratorApplicationTests {

    @Autowired
    private ChineseSearchService chineseSearchService;

//    @Autowired
//    private SingleCharacterService singleCharacterService;
//
//    @Autowired
//    private SingleCharacterMapper singleCharacterMapper;

    @Test
    void contextLoads() throws Exception {
    }

}
