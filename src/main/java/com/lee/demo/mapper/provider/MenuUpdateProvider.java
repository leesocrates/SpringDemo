package com.lee.demo.mapper.provider;

import com.lee.demo.model.menu.MenuRequest;
import org.apache.ibatis.jdbc.SQL;

public class MenuUpdateProvider {
    public String updateMenu(MenuRequest menuRequest){
        SQL sql = new SQL().UPDATE("menu");
        sql.SET("menu_desc=#{menuDesc}");
        sql.SET("update_time=#{updateTime}");
        sql.SET("name=#{name}");
        if(menuRequest.getImageUrl()!=null && menuRequest.getImageUrl().length()>0){
            sql.SET("img_url=#{imageUrl}");
        }
        sql.WHERE("menu_id=#{menuId}");
        return sql.toString();
    }
}
