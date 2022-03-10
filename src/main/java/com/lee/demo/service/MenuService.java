package com.lee.demo.service;

import com.github.pagehelper.PageInfo;
import com.lee.demo.model.menu.MenuRequest;
import com.lee.demo.model.menu.MenuResponse;

import java.util.List;


public interface MenuService {
    int insert(MenuRequest menuRequest);

    int update(MenuRequest menuRequest);

    MenuResponse select(MenuRequest menuRequest);

    List<MenuResponse> selectList(MenuRequest menuRequest);

    int countItem(MenuRequest menuRequest);

    PageInfo<MenuResponse> findItemByPage(int currentPage, int pageSize, MenuRequest request);

    PageInfo<MenuResponse> searchByKeyword(int currentPage, int pageSize, String userId, String keyword);
}
