package com.lee.demo.service.impl;

import com.lee.demo.model.user.UpdateUser;
import com.lee.demo.mapper.UserMapper;
import com.lee.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int insert(UpdateUser updateUser) {
        return userMapper.insert(updateUser);
    }

    @Override
    public UpdateUser select(UpdateUser updateUser) {
        return userMapper.select(updateUser);
    }

    @Override
    public List<UpdateUser> selectList(UpdateUser updateUser) {
        return userMapper.selectList(updateUser);
    }
}
