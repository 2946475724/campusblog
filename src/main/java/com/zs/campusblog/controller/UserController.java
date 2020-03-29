package com.zs.campusblog.controller;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.UserLoginDTO;
import com.zs.campusblog.mbg.model.Permission;
import com.zs.campusblog.mbg.model.User;
import com.zs.campusblog.service.RoleService;
import com.zs.campusblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zs
 * @date 2020/1/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public Result<User> register(@RequestBody User userParam, BindingResult result) {
        User user = userService.register(userParam);
        if (user == null) {
            Result.failed();
        }
        return Result.success(user);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO, BindingResult result) {
        String token = userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping(value = "/info")
    public Result getUserInfo(Principal principal) {
        if (principal == null) {
            return Result.unauthorized(null);
        }
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("menus", roleService.getMenuList(user.getId()));
        data.put("icon", user.getIcon());
        return Result.success(data);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping(value = "/logout")
    public Result logout() {
        return Result.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<CommonPage<User>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<User> adminList = userService.list(keyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping(value = "/permission/{userId}")
    public Result<List<Permission>> getPermissionList(@PathVariable Integer userId) {
        List<Permission> permissionList = userService.getPermissionList(userId);
        return Result.success(permissionList);
    }

}
