package com.lee.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.demo.model.menu.MenuRequest;
import com.lee.demo.model.menu.MenuResponse;
import com.lee.demo.mapper.MenuMapper;
import com.lee.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public int insert(MenuRequest menuRequest) {
        return menuMapper.insert(menuRequest);
    }

    @Override
    public int update(MenuRequest menuRequest) {
        return menuMapper.update(menuRequest);
    }

    @Override
    public MenuResponse select(MenuRequest menuRequest) {
        return menuMapper.select(menuRequest);
    }

    @Override
    public List<MenuResponse> selectList(MenuRequest menuRequest) {
        return menuMapper.selectList(menuRequest.getUserId());
    }

    @Override
    public int countItem(MenuRequest menuRequest) {
        return menuMapper.countItem(menuRequest);
    }

    @Override
    public PageInfo<MenuResponse> findItemByPage(int currentPage, int pageSize, String userId) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<MenuResponse> allItems = menuMapper.selectList(userId);        //全部商品

        return new PageInfo<>(allItems);
    }

    @Override
    public PageInfo<MenuResponse> searchByKeyword(int currentPage, int pageSize, String userId, String keyword) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<MenuResponse> allItems = menuMapper.searchKeyword(userId, "%"+keyword+"%");        //全部商品

        return new PageInfo<>(allItems);
    }

}
