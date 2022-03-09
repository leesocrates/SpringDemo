package com.lee.demo.controller;

import com.github.pagehelper.PageHelper;
import com.lee.demo.bean.BaseResponse;
import com.lee.demo.bean.menu.MenuRequest;
import com.lee.demo.bean.menu.MenuResponse;
import com.lee.demo.constants.ResponseCode;
import com.lee.demo.mapper.menu.MenuService;
import com.lee.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping(path = "/menu")
    public BaseResponse<MenuResponse> addMenu(@RequestBody MenuRequest request){
        BaseResponse<MenuResponse> baseResponse = new BaseResponse<>();
        int id = menuService.insert(request);
        if (id >= 1) {
            baseResponse.setCode(ResponseCode.SUCCESS);
            baseResponse.setMessage("菜单保存成功");
            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setMenuId(id);
            baseResponse.setData(menuResponse);
        } else {
            baseResponse.setCode(ResponseCode.ERROR.MENU_ADD_FAIL);
            baseResponse.setMessage("菜单保存失败");
        }
        return baseResponse;
    }

    @GetMapping(path = "/menu")
    public BaseResponse<List<MenuResponse>> getMenuList(@RequestParam Map<String, String> request){
        BaseResponse<List<MenuResponse>> baseResponse = new BaseResponse<>();
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setUserId((String) request.get("userId"));
        List<MenuResponse> list = findItemByPage(Integer.parseInt(request.get("currentPage")), Integer.parseInt(request.get("pageSize")), menuRequest);
        if (list!=null) {
            baseResponse.setCode(ResponseCode.SUCCESS);
            baseResponse.setMessage("菜单查询成功");
            baseResponse.setData(list);
        } else {
            baseResponse.setCode(ResponseCode.ERROR.MENU_QUERY_FAIL);
            baseResponse.setMessage("菜单查询失败");
        }
        return baseResponse;
    }

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
