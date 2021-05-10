package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MingjuModel implements Serializable {

    private Integer id;
    private String orderId;
    private String mingpen;
    private String xingge;
    private String xueli;
    private String caifu;
    private String caifushiye;
    private String diwei;
    private String liuqin;
    private String jibing;
    private String shiye;
    private String yiji;
    private String xiongzai;
    private String guansha;
}
