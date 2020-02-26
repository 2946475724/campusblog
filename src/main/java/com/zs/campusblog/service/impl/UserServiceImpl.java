package com.zs.campusblog.service.impl;

import com.zs.campusblog.entity.User;
import com.zs.campusblog.mapper.UserMapper;
import com.zs.campusblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2019/12/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名查询用户的所有信息
     * @param s 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    public int userRegister(String username, String password) {
        //如果用户名存在，返回错误
        if(userMapper.getUserByUsername(username) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return userMapper.addUser(username, encode);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }
}
