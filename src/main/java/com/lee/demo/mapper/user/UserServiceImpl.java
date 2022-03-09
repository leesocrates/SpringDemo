package com.lee.demo.mapper.user;

import com.lee.demo.bean.user.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserService userService;

    @Override
    public int insert(UpdateUser updateUser) {
        return userService.insert(updateUser);
    }

    @Override
    public UpdateUser select(UpdateUser updateUser) {
        return userService.select(updateUser);
    }

    @Override
    public List<UpdateUser> selectList(UpdateUser updateUser) {
        return userService.selectList(updateUser);
    }
}
