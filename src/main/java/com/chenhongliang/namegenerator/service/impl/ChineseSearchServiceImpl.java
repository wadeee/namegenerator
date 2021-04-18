package com.chenhongliang.namegenerator.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenhongliang.namegenerator.service.ChineseSearchService;
import com.chenhongliang.namegenerator.util.AuthUtils;
import com.chenhongliang.namegenerator.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChineseSearchServiceImpl implements ChineseSearchService {

    @Override
    public JSONObject search(String key) throws Exception {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> queries = new HashMap<>();

        String host = "https://aip.baidubce.com";
        String path = "/rpc/2.0/kg/v1/cognitive/chinese_search";
        headers.put("Content-Type", "application/json");
        queries.put("access_token", AuthUtils.getAuth());
        JSONObject json = JSONObject.parseObject("{\"query\": \"" + key + "\"}");

        HttpResponse response = HttpUtils.doPost(host, path, headers, queries, json);

        String result = EntityUtils.toString(response.getEntity()).replace("@", "");

        return JSON.parseObject(result);
    }
}
