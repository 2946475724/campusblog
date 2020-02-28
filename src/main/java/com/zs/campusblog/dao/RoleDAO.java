package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Menu;
import com.zs.campusblog.mbg.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/28
 * 后台用户角色自定义DAO
 */
public interface RoleDAO {
    List<Menu> getMenuList(@Param("userId") Integer userId);

    List<Menu> getMenuListByRoleId(@Param("roleId") Integer roleId);

    List<Resource> getResourceListByRoleId(@Param("roleId") Integer roleId);
}
