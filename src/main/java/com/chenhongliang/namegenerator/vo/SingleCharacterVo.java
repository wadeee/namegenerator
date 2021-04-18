package com.chenhongliang.namegenerator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleCharacterVo implements Serializable {
    private String boyCharacters;
    private String girlCharacters;
}
