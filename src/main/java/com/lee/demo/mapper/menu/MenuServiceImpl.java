package com.lee.demo.mapper.menu;

import com.github.pagehelper.PageHelper;
import com.lee.demo.bean.menu.MenuRequest;
import com.lee.demo.bean.menu.MenuResponse;
import com.lee.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MenuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuService menuService;

    @Override
    public int insert(MenuRequest menuRequest) {
        return menuService.insert(menuRequest);
    }

    @Override
    public MenuResponse select(MenuRequest menuRequest) {
        return menuService.select(menuRequest);
    }

    @Override
    public List<MenuResponse> selectList(MenuRequest menuRequest) {
        return menuService.selectList(menuRequest);
    }

    @Override
    public int countItem(MenuRequest menuRequest) {
        return menuService.countItem(menuRequest);
    }

    @Override
    public List<MenuResponse> findItemByPage(int currentPage, int pageSize, MenuRequest request) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        List<MenuResponse> allItems = menuService.selectList(request);        //全部商品
        int countNums = menuService.countItem(request);            //总记录数
        PageBean<MenuResponse> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();
    }

}
