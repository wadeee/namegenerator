package com.chenhongliang.namegenerator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class NamegeneratorApplicationTests {

    @Test
    void contextLoads() throws IOException {

        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("{point1}", "点1");
        replaceMap.put("{point2}", "点2");

        XWPFDocument doc = new XWPFDocument(new FileInputStream("D:\\template.docx"));

        /**
         * 替换段落中指定的文本
         */
        for(XWPFParagraph p : doc.getParagraphs()){
            List<XWPFRun> runs = p.getRuns();
            if(runs != null){
                for(XWPFRun r : runs){
                    //需要替换的文本
                    String text = r.getText(0);
                    //替换指定的文本
                    for(String key : replaceMap.keySet()){
                        if(text != null && text.equals(key)){
                            //替换的时候要注意，setText是有两个参数的
                            //第一个是替换的文本，第二个是从哪里开始替换
                            //0是替换全部，如果不设置那么默认就是从原文字
                            //结尾开始追加
                            // r.setText(map.get(key),0);

                            r.addBreak();//换行
                            r.setText(replaceMap.get(key), 0);
                            r.addBreak();
                            r.setText("############");
                        }
                    }
                }
            }
        }

        FileOutputStream outStream = new FileOutputStream("D:\\template1.docx");
        doc.write(outStream);
        outStream.close();

    }

}
