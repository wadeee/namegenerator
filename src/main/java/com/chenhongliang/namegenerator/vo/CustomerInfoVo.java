package com.chenhongliang.namegenerator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoVo implements Serializable {

    private String id;
    private String wechat;
    private String wechatMachine;
    private String salesman;
    private String visitDate;

}
