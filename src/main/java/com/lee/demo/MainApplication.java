package com.lee.demo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

//@EnableApolloConfig
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        System.setProperty("env","DEV");
//        System.setProperty("apollo.configService", "http://106.14.1.219:8100");
        SpringApplication.run(MainApplication.class, args);
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}

