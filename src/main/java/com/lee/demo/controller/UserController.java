package com.lee.demo.controller;

import com.lee.demo.auth.JwtUtils;
import com.lee.demo.constants.ResponseCode;
import com.lee.demo.utils.SnowFlake;
import com.lee.demo.bean.BaseResponse;
import com.lee.demo.bean.LoginResponse;
import com.lee.demo.bean.UpdateUser;
import com.lee.demo.mapper.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SnowFlake snowFlake;

    @ResponseBody
    @PostMapping(path = "/register")
    @CrossOrigin
    public BaseResponse<UpdateUser> register(@RequestBody UpdateUser updateUser) {
        BaseResponse<UpdateUser> baseResponse = new BaseResponse<>();
        List<UpdateUser> updateUserList = userService.selectList(updateUser);
        if(updateUserList!=null && updateUserList.size()>0){
            baseResponse.setCode(ResponseCode.ERROR.REGISTER_USER_EXIST);
            baseResponse.setMessage("用户已存在");
            return baseResponse;
        }

        updateUser.setPassword(DigestUtils.md5Hex(updateUser.getPassword()));
        updateUser.setUserId(snowFlake.nextId()+"");
        int id = userService.insert(updateUser);
        if (id >= 1) {
            baseResponse.setCode(ResponseCode.SUCCESS);
            baseResponse.setMessage("注册成功");
            updateUser.setPassword("");
            baseResponse.setData(updateUser);
        } else {
            baseResponse.setCode(ResponseCode.ERROR.REGISTER_UN_KNOW);
            baseResponse.setMessage("注册失败");
        }
        return baseResponse;
    }

    @ResponseBody
    @PostMapping(path = "/login")
    @CrossOrigin
    public BaseResponse<LoginResponse> login(@RequestBody UpdateUser updateUser) {
        UpdateUser dbUser = userService.select(updateUser);
        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        if(dbUser!=null){
            if(dbUser.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(updateUser.getPassword()))){
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(JwtUtils.generateToken(dbUser.getUserId()));
                loginResponse.setUserId(dbUser.getUserId());
                baseResponse.setCode(ResponseCode.SUCCESS);
                baseResponse.setMessage("login success");
                baseResponse.setData(loginResponse);
                return baseResponse;
            } else {

            }
        } else {

        }
        baseResponse.setCode(ResponseCode.ERROR.LOGIN_NOT_MATCH);
        baseResponse.setMessage("login failed");
        return baseResponse;
    }


}
