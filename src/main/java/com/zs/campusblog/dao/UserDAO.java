package com.zs.campusblog.dao;

import com.zs.campusblog.dto.UserDTO;
import com.zs.campusblog.mbg.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * 根据用户id查询用户信息
     */
    @Select("select * from user where id = #{id} and delete_status = 1 adn status = 1")
    User getUserById(Integer id);

}
