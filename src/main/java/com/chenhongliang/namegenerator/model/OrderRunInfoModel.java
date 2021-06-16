package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRunInfoModel implements Serializable {
    private Integer id;
    private Integer orderId;
    private String name;
    private String wuxing;
    private String meaning;
}
