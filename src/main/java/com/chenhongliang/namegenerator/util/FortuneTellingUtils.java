package com.chenhongliang.namegenerator.util;

import com.alibaba.fastjson.JSON;
import com.chenhongliang.namegenerator.form.OrderForm;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class FortuneTellingUtils {

    public static List<String> getXiYongShen(OrderForm orderForm) {

        Map<String, String> querys = new HashMap<>();
        querys.put("birthday", orderForm.getBirthday());
        querys.put("hour", orderForm.getBirthdayHour());
        querys.put("minute", orderForm.getBirthdayMinute());
        querys.put("ming", "");
        querys.put("pay", "1");
        querys.put("sex", orderForm.getSex().equals("未知")?"":orderForm.getSex());
        querys.put("xing", orderForm.getLastname());

        //八字命盘
        Map<String, String> result = getMapFromAPI("/openapi/bazi/getMingpen", querys);
        return getWuxingFromString(result.get("YongShen")+result.get("XiShen"));
    }

    private static List<String> getWuxingFromString(String str) {
        List<String> wuxingList = new ArrayList<>();
        wuxingList.add("金");
        wuxingList.add("木");
        wuxingList.add("水");
        wuxingList.add("火");
        wuxingList.add("土");
        Set<String> result = new HashSet<>();
        for (String xing: wuxingList) {
            if (str.indexOf(xing)>=0) {
                result.add(xing);
            }
        }
        return new ArrayList<>(result);
    }


    private static Map getMapFromAPI(String path, Map<String, String> querys){
        String host = "https://openapi.fatebox.cn";
        String method = "GET";
        String appcode = "32cf3b4f21904b27bd7877354307b724";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, headers, querys);
            String result = EntityUtils.toString(response.getEntity());
            Map mapType = JSON.parseObject(result, LinkedHashMap.class);
            return mapType;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

}
