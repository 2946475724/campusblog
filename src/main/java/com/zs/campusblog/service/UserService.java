package com.zs.campusblog.service;

import com.zs.campusblog.dto.UpdateUserPasswordDTO;
import com.zs.campusblog.dto.UserDTO;
import com.zs.campusblog.mbg.model.Permission;
import com.zs.campusblog.mbg.model.Resource;
import com.zs.campusblog.mbg.model.Role;
import com.zs.campusblog.mbg.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author zs
 * @date 2019/12/29
 */
public interface UserService {
    /**
     * 根据用户名获取用户
     */
    User getUserByUsername(String username);

    /**
     * 注册
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
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String WebLogin(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    User getUserById(Integer id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UserDTO> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     *
     */
    int update(User user);

    /**
     * 删除指定用户
     */
    int delete(Integer id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Integer userId, List<Integer> roleIds);

    /**
     * 获取用户角色
     */
    List<Role> getRoleList(Integer userId);

    /**
     * 获取指定用户的可访问权限
     */
    List<Resource> getResourceList(Integer userId);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     * @param userId
     * @return
     */
    List<Permission> getPermissionList(Integer userId);

    /**
     * 修改用户的+-权限
     */
    @Transactional
    int updatePermission(Integer userId, List<Integer> permissions);

    /**
     * 修改密码
     */
    int updatePassword(UpdateUserPasswordDTO updateUserPasswordDTO);

    /**
     * 获取用户信息
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取用户数量
     */
    int getUserCount();

    /**
     * 关注用户
     */

    void follow(Integer userId, Integer followingId);

    /**
     * 取消关注
     */
    void unFollow(Integer userId, Integer followingId);

    /**
     * 获取用户关注的人的信息
     */
    List<User> getFollowings(Integer userId);

    /**
     * 获取关注的人的Id
     */
    Set<String> getFollowingIds(Integer userId);

    /**
     * 获取关注数
     */
    Long getFollowNum(Integer userId);

    /**
     * 获取粉丝列表
     */
    Set<String> getFans(Integer followingId);

    /**
     * 获取粉丝数
     */
    Long getFansNum(Integer followingId);

}
