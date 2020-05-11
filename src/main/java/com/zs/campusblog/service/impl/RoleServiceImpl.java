package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dao.RoleDAO;
import com.zs.campusblog.dao.UserRoleRelationDAO;
import com.zs.campusblog.mbg.mapper.RoleMapper;
import com.zs.campusblog.mbg.mapper.RoleMenuRelationMapper;
import com.zs.campusblog.mbg.mapper.RoleResourceRelationMapper;
import com.zs.campusblog.mbg.model.*;
import com.zs.campusblog.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    RoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    RoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserRoleRelationDAO userRoleRelationDAO;

    @Override
    public int add(Role role) {
        role.setCreateTime(new Date());
        return roleMapper.insertSelective(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
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
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<Role> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        RoleExample example = new RoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andRoleNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Menu> getMenuList(Integer userId) {
        return roleDAO.getMenuList(userId);
    }

    @Override
    public List<Menu> listMenu(Integer roleId) {
        return roleDAO.getMenuListByRoleId(roleId);
    }

    @Override
    public List<Resource> listResource(Integer roleId) {
        return roleDAO.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Integer roleId, List<Integer> menuIds) {
        // 先删除原有的关系
        RoleMenuRelationExample example = new RoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        // 批量插入新关系
        for (Integer menuId : menuIds) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleId(roleId);
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelationMapper.insert(roleMenuRelation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Integer roleId, List<Integer> resourceIds) {
        // 先删除原有关系
        RoleResourceRelationExample example = new RoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        // 批量插入新关系
        for (Integer resourceId : resourceIds) {
            RoleResourceRelation relation = new RoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        return resourceIds.size();
    }

    @Override
    public List<Role> getRoleByRoleName(String roleName) {
        RoleExample example = new RoleExample();
        example.createCriteria().andRoleNameEqualTo(roleName);
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Role> getRoleListByUserId(Integer userId) {
        return roleDAO.getRoleListByUserId(userId);
    }

    @Override
    public int updateUserRole(Integer roleId, Integer userId) {
        return userRoleRelationDAO.updateUserRole(roleId, userId);
    }


}
