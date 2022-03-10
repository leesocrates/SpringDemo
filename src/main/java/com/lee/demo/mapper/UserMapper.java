package com.lee.demo.mapper;

import com.lee.demo.model.user.UpdateUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user(username, password, user_id) values(#{username},#{password},#{userId}) ")
    int insert(UpdateUser updateUser);

    @Select("select * from user where username = #{username}")
    UpdateUser select(UpdateUser updateUser);

    @Select("select * from user where username = #{username}")
    List<UpdateUser> selectList(UpdateUser updateUser);
}
