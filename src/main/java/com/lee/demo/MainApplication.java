package com.lee.demo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        System.setProperty("env","DEV");
        System.setProperty("apollo.configService", "http://106.14.1.219:8100");
        SpringApplication.run(MainApplication.class, args);
    }
}

