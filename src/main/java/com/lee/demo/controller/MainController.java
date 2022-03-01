package com.lee.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Value(value = "${helloStr: 默认名字}")
    private String helloStr;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @CrossOrigin
    public String hello() {
        return "Hello World!"+helloStr;
    }

}
