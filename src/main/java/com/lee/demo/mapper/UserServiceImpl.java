package com.lee.demo.mapper;

import com.lee.demo.bean.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserMapper")
public class UserServiceImpl implements UserService {
    @Autowired
    UserService userMapper;

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
