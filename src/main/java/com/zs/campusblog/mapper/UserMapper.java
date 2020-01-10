package com.zs.campusblog.mapper;

import com.zs.campusblog.entity.Role;
import com.zs.campusblog.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zs
 * @date 2019/12/29
 */

public interface UserMapper extends Mapper<User> {

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id获取用户对应的角色
     * @param id 用户id
     * @return
     */
    List<Role> getRolesByUserId(Integer id);

    /**
     * 添加用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
    int addUser(@Param("username") String username, @Param("password") String password);

}
