package com.chenhongliang.namegenerator.controller;

import com.alibaba.fastjson.JSON;
import com.chenhongliang.namegenerator.constant.Constant;
import com.chenhongliang.namegenerator.util.HttpUtils;
import com.chenhongliang.namegenerator.util.LunarUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/hongyan")
public class HongyanController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "红铟API请求");
        return "hongyan/index";
    }

    @PostMapping("/result")
    public String result(@RequestParam(value = "lastName") String lastName,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "sex") String sex,
                         @RequestParam(value = "date") String date,
                         @RequestParam(value = "hour") String hour,
                         @RequestParam(value = "minute") String minute,
                         @Nullable @RequestParam(value = "payed") String payed,
                         @Nullable @RequestParam(value = "service") Set<String> service,
                         Model model) throws IOException, ParseException {
        model.addAttribute("pinyinMap", Constant.hongyanTranslateMap);

        Map<String, String> formInfo = new LinkedHashMap<>();
        formInfo.put("姓", lastName);
        formInfo.put("名", name);
        formInfo.put("性别", sex);
        formInfo.put("出生日期", date);
        formInfo.put("出生日期(农历)", solarToLunar(date));
        formInfo.put("出生 时", hour);
        formInfo.put("出生 分", minute);
        formInfo.put("是否付款", "payed".equals(payed) ? "是" : "否");

        Map<String, String> querys = new HashMap<>();
        querys.put("birthday", date);
        querys.put("hour", hour);
        querys.put("ming", name);
        querys.put("minute", minute);
        querys.put("pay", "payed".equals(payed) ? "1" : "0");
        querys.put("sex", sex);
        querys.put("xing", lastName);

        model.addAttribute("formInfo", formInfo);
        model.addAttribute("title", "红铟API请求");

        if (service != null) {
            if (service.contains("mingpen")) {
                //八字命盘
                model.addAttribute("mingpen", getMapFromAPI("/openapi/bazi/getMingpen", querys));
            }
            if (service.contains("mingju")) {
                //命局分析
                model.addAttribute("mingju", getMapFromAPI("/openapi/bazi/getMingju", querys));
            }
        }
        return "hongyan/result";
    }

    private Map getMapFromAPI(String path, Map<String, String> querys){
        String host = "https://openapi.fatebox.cn";
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
            return JSON.parseObject(result, LinkedHashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    private String solarToLunar(String date) throws ParseException {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        today.setTime(formatter.parse(date));
        LunarUtils lunarUtils = new LunarUtils(today);
        return lunarUtils.cyclical() + "年" + lunarUtils.toString();
    }

}
