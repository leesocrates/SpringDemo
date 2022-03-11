package com.lee.demo.mapper;

import com.lee.demo.mapper.provider.MenuUpdateProvider;
import com.lee.demo.model.menu.MenuRequest;
import com.lee.demo.model.menu.MenuResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Insert("insert into menu(name, menu_desc, user_id,menu_id, img_url, create_time, update_time) values(#{name},#{menuDesc},#{userId},#{menuId}, #{imageUrl}, #{createTime}, #{updateTime})")
    int insert(MenuRequest menuRequest);

//    @Update("update menu set menu_desc=#{menuDesc}, update_time=#{updateTime}, name=#{name}, img_url=#{imageUrl} where menu_id=#{menuId}")
    @UpdateProvider(type = MenuUpdateProvider.class, method = "updateMenu")
    int update(MenuRequest menuRequest);

    @Select("select * from menu where menu_id = #{menuId}")
    MenuResponse select(MenuRequest menuRequest);

    @Select("select * from menu where user_id = #{userId}")
    List<MenuResponse> selectList(@Param("userId") String userId);

    @Select("select count(id) from menu where user_id = #{userId}")
    int countItem(MenuRequest menuRequest);

    @Select("select * from menu where user_id = #{userId} and (menu_desc like #{keyword} or name like #{keyword})")
    List<MenuResponse> searchKeyword(@Param("userId") String userId, @Param("keyword") String keyword);

}
