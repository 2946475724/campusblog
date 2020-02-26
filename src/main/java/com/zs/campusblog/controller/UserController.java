package com.zs.campusblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.campusblog.entity.PageBaseInfo;
import com.zs.campusblog.entity.Result;
import com.zs.campusblog.entity.User;
import com.zs.campusblog.service.RoleService;
import com.zs.campusblog.service.UserService;
import com.zs.campusblog.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    /**
     * 获取所有用户
     * @return
     */
    @ApiOperation(value = "获取所有用户")
    @GetMapping(value = "/list")
    public Result<User> getUsers(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.getUsers();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        int totalPages = pageInfo.getPages();
        int pageNum1 = pageInfo.getPageNum();
        int total = (int) pageInfo.getTotal();
        return ResultUtil.success("获取成功" , users, new PageBaseInfo(pageNum1, totalPages, total));
    }

}
