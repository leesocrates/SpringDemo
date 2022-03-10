package com.lee.demo.service;

import com.lee.demo.model.user.UpdateUser;

import java.util.List;

public interface UserService {
    int insert(UpdateUser updateUser);

    UpdateUser select(UpdateUser updateUser);

    List<UpdateUser> selectList(UpdateUser updateUser);
}
