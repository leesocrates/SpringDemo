package com.lee.demo.bean.user;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String userId;

}
