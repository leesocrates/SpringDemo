package com.lee.demo.controller;

import com.github.pagehelper.PageInfo;
import com.lee.demo.model.BaseResponse;
import com.lee.demo.model.menu.MenuRequest;
import com.lee.demo.model.menu.MenuResponse;
import com.lee.demo.constants.ResponseCode;
import com.lee.demo.service.MenuService;
import com.lee.demo.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    SnowFlake snowFlake;

    @PostMapping(path = "/menu")
    public BaseResponse<MenuResponse> addMenu(@RequestBody MenuRequest request) {
        BaseResponse<MenuResponse> baseResponse = new BaseResponse<>();
        long menuId = snowFlake.nextId();
        request.setMenuId(menuId);
        int id = menuService.insert(request);
        if (id >= 1) {
            baseResponse.setCode(ResponseCode.SUCCESS);
            baseResponse.setMessage("菜单保存成功");
            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setMenuId(menuId);
            baseResponse.setData(menuResponse);
        } else {
            baseResponse.setCode(ResponseCode.ERROR.MENU_ADD_FAIL);
            baseResponse.setMessage("菜单保存失败");
        }
        return baseResponse;
    }

    @PostMapping(path = "/menu/{menuId}")
    public BaseResponse<MenuResponse> updateMenu(@PathVariable("menuId")Long menuId, @RequestBody MenuRequest request) {
        BaseResponse<MenuResponse> baseResponse;
        request.setMenuId(menuId);
        int id = menuService.update(request);
        if (id >= 1) {
            baseResponse = BaseResponse.success(null);
        } else {
            baseResponse = BaseResponse.fail("菜单更新失败", ResponseCode.ERROR.MENU_UPDATE_FAIL);
        }
        return baseResponse;
    }

    @GetMapping(path = "/menu/search")
    public BaseResponse<PageInfo<MenuResponse>> searchMenu(@RequestParam Map<String, String> request) {
        BaseResponse<PageInfo<MenuResponse>> baseResponse;
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setUserId(request.get("userId"));
        PageInfo<MenuResponse> list = menuService.searchByKeyword(
                Integer.parseInt(request.get("currentPage")), Integer.parseInt(request.get("pageSize")),
                menuRequest.getUserId(), request.get("keyword"));
        if (list != null) {
            baseResponse = BaseResponse.success(list);
        } else {
            baseResponse = BaseResponse.fail(ResponseCode.ERROR.MENU_QUERY_FAIL, "菜单查询失败");
        }
        return baseResponse;
    }


}
