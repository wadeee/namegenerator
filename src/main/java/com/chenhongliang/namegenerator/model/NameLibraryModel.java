package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameLibraryModel implements Serializable {

    private Integer id;
    private String name;
    private String pinyin;
    private String meaning;
    private String wuxing;
    private String source;
    private Boolean male;
    private Boolean female;

}
