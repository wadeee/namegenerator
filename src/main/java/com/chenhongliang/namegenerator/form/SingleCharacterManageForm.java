package com.chenhongliang.namegenerator.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleCharacterManageForm implements Serializable {
    private Boolean male;
    private Boolean female;
    private String character;
    private String pinyin;
    private String meaning;
    private String wuxing;
    private String idiom;
    private String poetry;
}
