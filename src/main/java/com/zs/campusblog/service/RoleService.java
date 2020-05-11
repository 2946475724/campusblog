package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Menu;
import com.zs.campusblog.mbg.model.Permission;
import com.zs.campusblog.mbg.model.Resource;
import com.zs.campusblog.mbg.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/5
 * 角色管理
 */
public interface RoleService {

    /**
     * 添加角色
     */
    int add(Role role);

    /**
     * 修改角色
     */
    int update(Role role);

    /**
     * 批量删除角色
     */
    int deleteById(Integer id);

    /**
     * 获取指定角色权限
     */
    List<Permission> getPermissionList(Integer roleId);

    /**
     * 修改指定角色的权限
     */
    @Transactional
    int updatePermission(Integer roleId, List<Integer> permissionIds);

    /**
     * 获取所有角色列表
     */
    List<Role> list();

    /**
     * 分页获取角色列表
     */
    List<Role> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<Menu> getMenuList(Integer userId);

    /**
     * 获取角色相关菜单
     */
    List<Menu> listMenu(Integer roleId);

    /**
     * 获取角色相关资源
     */
    List<Resource> listResource(Integer roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Integer roleId, List<Integer> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Integer roleId, List<Integer> resourceIds);

    /**
     * 根据角色名获取角色信息
     */
    List<Role> getRoleByRoleName(String roleName);

    /**
     * 根据用户id获取角色信息
     */
    List<Role> getRoleListByUserId(Integer userId);

    /**
     * 更新用户角色
     */
    int updateUserRole(Integer roleId, Integer userId);
}
