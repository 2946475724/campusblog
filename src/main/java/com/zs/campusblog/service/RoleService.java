package com.zs.campusblog.service;

import com.zs.campusblog.entity.Role;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/5
 */
public interface RoleService {
    List<Role> roles();

    int addNewRole(String role, String description);

    int deleteRoleById(Integer rid);
}
