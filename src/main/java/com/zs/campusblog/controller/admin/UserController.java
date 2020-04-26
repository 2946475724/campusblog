package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.common.aop.LogAop;
import com.zs.campusblog.component.RedisFollowHelper;
import com.zs.campusblog.dto.UserDTO;
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
import java.util.Set;

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
    @Autowired
    private RedisFollowHelper redisFollowHelper;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public Result<User> register(@RequestBody User userParam, BindingResult result) {
        User user = userService.register(userParam);
        if (user == null) {
            Result.failed();
        }
        return Result.success(user);
    }

    @LogAop("用户登录")
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
        data.put("roles", roleService.getRoleListByUserId(user.getId()));
        data.put("menus", roleService.getMenuList(user.getId()));
        data.put("icon", user.getIcon());
        return Result.success(data);
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete/{id}")
    public Result deleteUser(@PathVariable(name = "id") Integer id) {
        int result = userService.delete(id);
        if (result > 0) {
            return Result.success("", "删除成功");
        }
        return Result.failed("删除失败");
    }

    @ApiOperation(value = "登出功能")
    @PostMapping(value = "/logout")
    public Result logout() {
        return Result.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<CommonPage<UserDTO>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UserDTO> adminList = userService.list(keyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping(value = "/permission/{userId}")
    public Result<List<Permission>> getPermissionList(@PathVariable Integer userId) {
        List<Permission> permissionList = userService.getPermissionList(userId);
        return Result.success(permissionList);
    }

    @ApiOperation("前台获取用户信息")
    @GetMapping("/setting/info")
    public Result getUser(Principal principal) {
        if (principal == null) {
            return Result.unauthorized(null);
        }
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        return Result.success(user);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/setting/update")
    public Result updateUserInfo(@RequestBody User user) {
        if (userService.update(user) > 0) {
            return Result.success(null, "更新用户信息成功");
        }
        return Result.success(null, "更新用户信息失败");
    }

    @ApiOperation("关注用户")
    @PostMapping("/follow")
    public Result follow(Integer userId, Integer followingId) {
        redisFollowHelper.follow(userId, followingId);
        return Result.success("", "关注成功");
    }

    @ApiOperation("取消关注")
    @PostMapping("/unFollow")
    public Result unFollow(Integer userId, Integer followingId) {
        redisFollowHelper.unFollow(userId, followingId);
        return Result.success("", "取消关注成功");
    }

    @ApiOperation("获取关注用户的id")
    @PostMapping("/follows")
    public Result getFollowings(Integer userId) {
        Set<String> followings = redisFollowHelper.getFollowings(userId);
        Map<String, Set<String>> map = new HashMap<>();
        map.put("follows", followings);
        return Result.success(map);
    }

    @ApiOperation("获取粉丝列表")
    @PostMapping("/fans")
    public Result getFans(Integer followingId) {
        Set<String> fans = redisFollowHelper.getFans(followingId);
        Map<String, Set<String>> map = new HashMap<>();
        map.put("fans", fans);
        return Result.success(map);
    }

    @ApiOperation("获取当前网站用户数")
    @GetMapping("/count")
    public Result getUserCount() {
        return Result.success(userService.getUserCount());
    }

}
