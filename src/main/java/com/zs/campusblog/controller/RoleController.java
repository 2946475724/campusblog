package com.zs.campusblog.controller;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.Role;
import com.zs.campusblog.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/28
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping(value = "/listAll")
    public Result<List<Role>> listAll() {
        List<Role> roleList = roleService.list();
        return Result.success(roleList);
    }
}
