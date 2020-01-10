package com.zs.campusblog.service.impl;

import com.zs.campusblog.entity.Role;
import com.zs.campusblog.mapper.RoleMapper;
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

    @Override
    public List<Role> roles() {
        return roleMapper.roles();
    }

    @Override
    public int addNewRole(String role, String description) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        return roleMapper.addNewRole(role, description);
    }

    @Override
    public int deleteRoleById(Integer rid) {
        return roleMapper.deleteRoleById(rid);
    }
}
