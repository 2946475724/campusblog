package com.zs.campusblog.dao;

import com.zs.campusblog.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    /**
     * 获取用户列表
     */
    List<UserDTO> getUserList(@Param("keyword") String keyword);

    /**
     * 删除用户
     */
    int delete(Integer userId);

    /**
     * 获取用户数量
     */
    int getUserCount();

    /**
     * 根据用户名查询用户信息
     */

}
