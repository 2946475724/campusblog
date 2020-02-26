package com.zs.campusblog.service;

import com.zs.campusblog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2019/12/29
 */
@Service
public interface UserService extends UserDetailsService {

    /**
     * 获取所有用户
     * @return
     */
    List<User> getUsers();
}
