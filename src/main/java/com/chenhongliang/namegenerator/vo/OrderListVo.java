package com.chenhongliang.namegenerator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListVo implements Serializable {

    private String id;
    private String orderNumber;
    private String plan;
    private Date deliveryTime;
    private String status;

}
