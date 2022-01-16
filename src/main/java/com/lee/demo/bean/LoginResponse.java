package com.lee.demo.bean;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String userId;

}
