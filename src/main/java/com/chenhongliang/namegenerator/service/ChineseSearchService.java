package com.chenhongliang.namegenerator.service;

import com.alibaba.fastjson.JSONObject;

public interface ChineseSearchService {
    JSONObject search(String key) throws Exception;
}
