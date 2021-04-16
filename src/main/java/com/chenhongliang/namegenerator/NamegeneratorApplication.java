package com.chenhongliang.namegenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenhongliang.namegenerator.mapper")
public class NamegeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NamegeneratorApplication.class, args);
    }

}
