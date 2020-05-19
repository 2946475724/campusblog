package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.UpdateUserPasswordDTO;
import com.zs.campusblog.mbg.model.User;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.RoleService;
import com.zs.campusblog.service.UserService;
import com.zs.campusblog.vo.UserVO;
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
 * @date 2020/3/5
 * 前台用户相关控制器
 */
@RestController
@RequestMapping("/api/user")
public class WebUserController {


    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "登录并返回token")
    @PostMapping(value = "/login")
    public Result login(@RequestBody UserVO userVO, BindingResult result) {
        String token = userService.WebLogin(userVO.getUsername(), userVO.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        User user = userService.getUserByUsername(userVO.getUsername());
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

    @ApiOperation("修改密码")
    @PostMapping("/setting/password")
    public Result updatePassword(@RequestBody UpdateUserPasswordDTO userPasswordDTO) {
        int result = userService.updatePassword(userPasswordDTO);
        if (result > 0) {
            return Result.success("", "更新密码成功");
        } else if (result == -1) {
            return Result.failed("密码不能为空");
        } else if (result == -2) {
            return Result.failed("不存在该用户");
        } else if (result == -3) {
            return Result.failed("原密码输入错误");
        }
        return Result.failed("未知错误，请联系管理员");
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/setting/update")
    public Result updateUserInfo(@RequestBody User user) {
        if (userService.update(user) > 0) {
            return Result.success(null, "更新用户信息成功");
        }
        return Result.success(null, "更新用户信息失败");
    }

    @ApiOperation("根据用户id获取文章列表")
    @GetMapping("/article")
    public Result getArticlesByUserId(@RequestParam(name = "userId") Integer id) {
        List<ArticleDTO> articles = articleService.getArticlesByUserId(id);
        return Result.success(articles);
    }

    @ApiOperation("关注用户")
    @PostMapping("/follow")
    public Result follow(Integer userId, Integer followingId) {
        userService.follow(userId, followingId);
        return Result.success("", "关注成功");
    }

    @ApiOperation("取消关注")
    @PostMapping("/unFollow")
    public Result unFollow(Integer userId, Integer followingId) {
        userService.unFollow(userId, followingId);
        return Result.success("", "取消关注成功");
    }

    @ApiOperation("获取关注用户的信息")
    @PostMapping("/follows")
    public Result getFollowings(Integer userId) {
        List<User> followings = userService.getFollowings(userId);
        Map<String, List<User>> map = new HashMap<>();
        map.put("follows", followings);
        return Result.success(map);
    }

    @ApiOperation("获取关注用户的id")
    @PostMapping("/followIds")
    public Result getFollowingIds(Integer userId) {
        Set<String> followingIds = userService.getFollowingIds(userId);
        Map<String, Set<String>> map = new HashMap<>();
        map.put("follows", followingIds);
        return Result.success(map);
    }

    @ApiOperation("获取粉丝列表")
    @PostMapping("/fans")
    public Result getFans(Integer followingId) {
        Set<String> fans = userService.getFans(followingId);
        Map<String, Set<String>> map = new HashMap<>();
        map.put("fans", fans);
        return Result.success(map);
    }

    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

}
