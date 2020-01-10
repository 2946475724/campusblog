package com.zs.campusblog.mapper;

import com.zs.campusblog.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/5
 */
public interface RoleMapper {

    /**
     * 获取所有角色
     * @return
     */
    List<Role> roles();

    /**
     * 添加角色
     * @param role
     * @param description
     * @return
     */
    int addNewRole(@Param("role") String role, @Param("description") String description);

    /**
     * 根据id删除角色
     * @param rid
     * @return
     */
    int deleteRoleById(Integer rid);

}
