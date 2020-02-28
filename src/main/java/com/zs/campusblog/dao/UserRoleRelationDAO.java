package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/27
 * 用户与角色管理自定义Dao
 */
public interface UserRoleRelationDAO {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<Permission> getPermissionList(@Param("userId") Integer userId);
}
