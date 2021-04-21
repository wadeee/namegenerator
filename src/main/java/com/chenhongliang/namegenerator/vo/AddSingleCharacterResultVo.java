package com.chenhongliang.namegenerator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSingleCharacterResultVo implements Serializable {

    private List<String> charactersAddFailed;
    private Map<String, List<String>> pinyinSelectMap;

}
