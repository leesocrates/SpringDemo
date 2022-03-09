package com.lee.demo.mapper.menu;

import com.lee.demo.bean.menu.MenuRequest;
import com.lee.demo.bean.menu.MenuResponse;
import com.lee.demo.bean.user.UpdateUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuService {
    @Insert("insert into menu(name, menu_desc, user_id, img_url, create_time, update_time) values(#{name},#{desc},#{userId}, #{imageUrl}, #{createTime}, #{updateTime})")
    int insert(MenuRequest menuRequest);

    @Select("select * from menu where menu_id = #{menuId}")
    MenuResponse select(MenuRequest menuRequest);

    @Select("select * from menu where user_id = #{userId}")
    List<MenuResponse> selectList(MenuRequest menuRequest);

    @Select("select count(id) from menu where user_id = #{userId}")
    int countItem(MenuRequest menuRequest);
    @Select("select count(id) from menu where user_id = 0")
    List<MenuResponse> findItemByPage(int currentPage,int pageSize, MenuRequest request);
}
