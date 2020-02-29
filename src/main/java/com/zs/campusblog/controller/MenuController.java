package com.zs.campusblog.controller;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.MenuNode;
import com.zs.campusblog.mbg.model.Menu;
import com.zs.campusblog.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/28
 * 后台菜单管理
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("添加后台菜单")
    @PostMapping(value = "/create")
    public Result create(@RequestBody Menu menu) {
        int count = menuService.create(menu);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("分页查询后台菜单")
    @GetMapping(value = "/list/{parentId}")
    public Result<CommonPage<Menu>> list(@PathVariable Integer parentId,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Menu> menuList = menuService.list(parentId, pageSize, pageNum);
        return Result.success(CommonPage.restPage(menuList));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping(value = "/treeList")
    public Result<List<MenuNode>> treeList() {
        List<MenuNode> list = menuService.treeList();
        return Result.success(list);
    }
}