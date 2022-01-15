package com.lee.demo.mapper;

import com.lee.demo.bean.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserMapper")
public class UserServiceImpl implements UserService {
    @Autowired
    UserService userMapper;

    @Override
    public int insert(UpdateUser updateUser) {
       return userMapper.insert(updateUser);
    }

    @Override
    public String select(UpdateUser updateUser) {
        return userMapper.select(updateUser);
    }
}
