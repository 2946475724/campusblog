package com.zs.campusblog.service.impl;

import com.zs.campusblog.dao.RoleDAO;
import com.zs.campusblog.mbg.mapper.RoleMapper;
import com.zs.campusblog.mbg.model.Menu;
import com.zs.campusblog.mbg.model.Permission;
import com.zs.campusblog.mbg.model.Resource;
import com.zs.campusblog.mbg.model.Role;
import com.zs.campusblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/5
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    private RoleDAO roleDAO;


    @Override
    public int create(Role role) {
        return 0;
    }

    @Override
    public int update(Integer id, Role role) {
        return 0;
    }

    @Override
    public int delete(List<Integer> ids) {
        return 0;
    }

    @Override
    public List<Permission> getPermissionList(Integer roleId) {
        return null;
    }

    @Override
    public int updatePermission(Integer roleId, List<Integer> permissionIds) {
        return 0;
    }

    @Override
    public List<Role> list() {
        return null;
    }

    @Override
    public List<Role> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<Menu> getMenuList(Integer userId) {
        return roleDAO.getMenuList(userId);
    }

    @Override
    public List<Menu> listMenu(Integer roleId) {
        return null;
    }

    @Override
    public List<Resource> listResource(Integer roleId) {
        return null;
    }

    @Override
    public int allocMenu(Long roleId, List<Integer> menuIds) {
        return 0;
    }

    @Override
    public int allocResource(Integer roleId, List<Integer> resourceIds) {
        return 0;
    }
}
