package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.User;
import com.zs.campusblog.service.UserService;
import com.zs.campusblog.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zs
 * @date 2020/3/8
 * 第三方登录认证
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @GetMapping(value = "/verify/{accessToken}")
    public Result verifyUser(@PathVariable("accessToken") String accessToken) {
        String username = jwtTokenUtil.getUserNameFromToken(accessToken);
        if (username != null) {
            User userInfo = userService.getUserByUsername(username);
            if (jwtTokenUtil.validateToken(accessToken, userInfo)) {
                return Result.success(userInfo);
            }
        }
        return Result.failed("token校验失败！");
    }
}
