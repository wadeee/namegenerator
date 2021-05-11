package com.chenhongliang.namegenerator.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm implements Serializable {

    private String orderNumber;
    private String salesman;
    private String wechatMachine;
    private String nameGiver;
    private String bills;
    private String plan;
    private Integer tillDeliveryTime;
    private String lastname;
    private String sex;
    private String nameSize;
    private String birthday;
    private String birthdayLunar;
    private String birthdayHour;
    private String birthdayMinute;
    private String bannedPinyin;
    private String bannedCharacter;
    private String generation;
    private String style;
    private String notes;
    private String status;
    private String wuxing;
    private String updateTime;
    private Boolean delivered;

}
