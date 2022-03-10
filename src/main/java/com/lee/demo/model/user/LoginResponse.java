package com.lee.demo.model.user;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String userId;

}
