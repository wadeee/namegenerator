package com.chenhongliang.namegenerator;

import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@SpringBootTest
class NamegeneratorApplicationTests {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Test
    void contextLoads() {
        List<String> stringList = new ArrayList<>();
        stringList.add("huo");
        stringList.add("huo");
        Set<String> stringSet = new HashSet<>(stringList);
        System.out.println(stringList.toString());
        System.out.println(stringSet.toString());
    }

}
