package com.chenhongliang.namegenerator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListVo implements Serializable {

    private String id;
    private String orderNumber;
    private String plan;
    private String deliveryTime;
    private String status;

}
