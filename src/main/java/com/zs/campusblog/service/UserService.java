package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Permission;
import com.zs.campusblog.mbg.model.User;

import java.util.List;

/**
 * @author zs
 * @date 2019/12/29
 */
public interface UserService {
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 注册
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     * @param userId
     * @return
     */
    List<Permission> getPermissionList(Integer userId);
}
