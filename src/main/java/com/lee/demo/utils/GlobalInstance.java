package com.lee.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalInstance {

    @Bean
    public SnowFlake snowFlake(){
        return new SnowFlake(1, 1);
    }
}
