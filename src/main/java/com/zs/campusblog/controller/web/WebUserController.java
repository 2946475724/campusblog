package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.UserLoginDTO;
import com.zs.campusblog.mbg.model.User;
import com.zs.campusblog.service.RoleService;
import com.zs.campusblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zs
 * @date 2020/3/5
 */
@RestController
@RequestMapping("/api/user")
public class WebUserController {

    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO, BindingResult result) {
        String token = userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        User user = userService.getUserByUsername(userLoginDTO.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        map.put("userInfo", user);
        return Result.success(map);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public Result<User> register(@RequestBody User userParam, BindingResult result) {
        User user = userService.register(userParam);
        if (user == null) {
            Result.failed();
        }
        return Result.success(user);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping(value = "/logout")
    public Result logout() {
        return Result.success(null);
    }

}
