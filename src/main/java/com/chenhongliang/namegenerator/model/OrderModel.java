package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel implements Serializable {

    private Integer id;
    private String orderNumber;
    private String salesman;
    private String wechatMachine;
    private String nameGiver;
    private String bills;
    private String plan;
    private String deliveryTime;
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
    private Integer generationPos;
    private String style;
    private String notes;
    private String status;
    private String wuxing;
    private String updateTime;
    private Boolean delivered;
    private Integer tillDeliveryTime;

}
