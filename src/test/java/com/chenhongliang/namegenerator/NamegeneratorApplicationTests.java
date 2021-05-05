package com.chenhongliang.namegenerator;

import com.chenhongliang.namegenerator.form.NameConstrainForm;
import com.chenhongliang.namegenerator.mapper.SingleCharacterMapper;
import com.chenhongliang.namegenerator.service.NameGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class NamegeneratorApplicationTests {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Autowired
    private SingleCharacterMapper singleCharacterMapper;

    @Test
    void contextLoads() throws Exception {
        NameConstrainForm nameConstrainForm = new NameConstrainForm();
        nameConstrainForm.setSex("未知");
        nameConstrainForm.setLastname("廖");
        nameConstrainForm.setNameSize(4);
        List<String> bannedPinyin = new ArrayList<>();
//        bannedPinyin.add("ji");
        bannedPinyin.add("yu");
        nameConstrainForm.setBannedPinyin(bannedPinyin);
        List<String> bannedCharacter = new ArrayList<>();
        bannedCharacter.add("还");
//        bannedCharacter.add("哄");
        nameConstrainForm.setBannedCharacter(bannedCharacter);
        List<String> wuxing = new ArrayList<>();
//        wuxing.add("木");
//        wuxing.add("水");
//        wuxing.add("金");
        nameConstrainForm.setWuxing(wuxing);
//        List<SingleCharacterModel> result = singleCharacterMapper.constrainedCharacters(nameConstrainForm);
//        for (SingleCharacterModel temp: result) {
//            System.out.println(temp.getCharacter());
//        }
        System.out.println(nameGeneratorService.newNameFromCharacter(nameConstrainForm).toString());

    }

}
