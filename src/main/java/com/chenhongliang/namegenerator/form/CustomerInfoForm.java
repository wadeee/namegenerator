package com.chenhongliang.namegenerator.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoForm implements Serializable {

    private String wechat;
    private String wechatMachine;
    private String salesman;
    private String birthday;

}
