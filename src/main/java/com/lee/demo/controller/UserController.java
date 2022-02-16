package com.lee.demo.controller;

import com.lee.demo.auth.JwtUtils;
import com.lee.demo.utils.SnowFlake;
import com.lee.demo.bean.BaseResponse;
import com.lee.demo.bean.LoginResponse;
import com.lee.demo.bean.UpdateUser;
import com.lee.demo.mapper.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SnowFlake snowFlake;

    @ResponseBody
    @PostMapping(path = "/register")
    public BaseResponse<UpdateUser> register(@RequestBody UpdateUser updateUser) {
        updateUser.setPassword(DigestUtils.md5Hex(updateUser.getPassword()));
        updateUser.setUserId(snowFlake.nextId()+"");
        int id = userService.insert(updateUser);
        BaseResponse<UpdateUser> baseResponse = new BaseResponse<>();
        if (id >= 1) {
            baseResponse.setCode("1");
            baseResponse.setMessage("注册成功");
            updateUser.setPassword("");
            baseResponse.setData(updateUser);
            return baseResponse;
        }
        baseResponse.setCode("-1");
        baseResponse.setMessage("注册失败");
        return baseResponse;
    }

    @ResponseBody
    @PostMapping(path = "/login")
    public BaseResponse<LoginResponse> login(@RequestBody UpdateUser updateUser) {
        UpdateUser dbUser = userService.select(updateUser);
        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        if(dbUser!=null){
            if(dbUser.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(updateUser.getPassword()))){
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(JwtUtils.generateToken(dbUser.getUserId()));
                loginResponse.setUserId(dbUser.getUserId());
                baseResponse.setCode("1");
                baseResponse.setMessage("login success");
                baseResponse.setData(loginResponse);
                return baseResponse;
            } else {

            }
        } else {

        }
        baseResponse.setCode("-2");
        baseResponse.setMessage("login failed");
        return baseResponse;
    }


}
