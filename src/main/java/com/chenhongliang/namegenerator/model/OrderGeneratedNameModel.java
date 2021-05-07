package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGeneratedNameModel implements Serializable {
    private Integer id;
    private Integer orderId;
    private String name;
    private String pinyin;
    private String wuxing;
    private String meaning;
    private String source;
    private Boolean namelibType;
}
