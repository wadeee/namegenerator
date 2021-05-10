package com.chenhongliang.namegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MingpenModel implements Serializable {

    private Integer id;
    private String orderId;
    private String zhuxing;
    private String tiangan;
    private String dizhi;
    private String dayun;
    private String yongshen;
    private String xishen;
    private String jishen;
    private String jiaoyunshijian;
    private String qiangruo;
    private String wuxing;
    private String mu;
    private String jin;
    private String shui;
    private String tu;
    private String huo;
}
