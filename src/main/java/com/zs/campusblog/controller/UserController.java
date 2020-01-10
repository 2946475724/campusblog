package com.zs.campusblog.controller;

import com.zs.campusblog.service.RoleService;
import com.zs.campusblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    public User getUserById(@PathVariable Integer userId) {
//        return ;
//    }
}
