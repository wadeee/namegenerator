package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleCharacterModel implements Serializable {

    private Integer id;
    private String character;
    private String pinyin;
    private String source;
    private String meaning;
    private String wuxing;
    private String idiom;
    private String poetry;

}
