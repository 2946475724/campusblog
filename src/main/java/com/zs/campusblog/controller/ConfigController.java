package com.zs.campusblog.controller;

import com.zs.campusblog.entity.Menu;
import com.zs.campusblog.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/5
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    MenuService menuService;

    @ApiOperation(value = "根据用户id获取菜单")
    @GetMapping("/menu")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }
}
