package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoModel implements Serializable {

    private Integer id;
    private String wechat;
    private String wechatMachine;
    private String salesman;
    private String birthday;
    private String visitDate;

}
