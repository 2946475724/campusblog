package com.zs.campusblog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dao.UserDAO;
import com.zs.campusblog.dao.UserRoleRelationDAO;
import com.zs.campusblog.dto.UpdateUserPasswordDTO;
import com.zs.campusblog.dto.UserDTO;
import com.zs.campusblog.dto.UserDetail;
import com.zs.campusblog.mbg.mapper.UserMapper;
import com.zs.campusblog.mbg.mapper.UserRoleRelationMapper;
import com.zs.campusblog.mbg.model.*;
import com.zs.campusblog.service.UserService;
import com.zs.campusblog.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zs
 * @date 2019/12/29
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleRelationDAO userRoleRelationDAO;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public User getUserByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User register(User userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setCreateTime(new Date());
        user.setStatus(1);
        user.setDeleteStatus(1);
        //查询是否有相同用户名的用户
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return null;
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        int result = userMapper.insert(user);
        if (result > 0) {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(user.getId());
            userRoleRelation.setRoleId(5);
            userRoleRelationMapper.insert(userRoleRelation);
        }
        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserDTO> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return userDAO.getUserList(keyword);
    }


    @Override
    public int update(User user) {
        User rawUser = userMapper.selectByPrimaryKey(user.getId());
        if (rawUser.getPassword().equals(user.getPassword())) {
            //与原加密密码相同的不需要修改
            user.setPassword(null);
        } else {
            //与原加密密码不同的需要加密修改
            if (user.getPassword().isEmpty()) {
                user.setPassword(null);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(Integer id) {
        return userDAO.delete(id);
    }

    @Override
    public int updateRole(Integer userId, List<Integer> roleIds) {
        return 0;
    }

    @Override
    public List<Role> getRoleList(Integer userId) {
        return userRoleRelationDAO.getRoleList(userId);
    }

    @Override
    public List<Resource> getResourceList(Integer userId) {
        return userRoleRelationDAO.getResourceList(userId);
    }

    @Override
    public List<Permission> getPermissionList(Integer userId) {
        return userRoleRelationDAO.getPermissionList(userId);
    }

    @Override
    public int updatePermission(Integer userId, List<Integer> permissions) {
        return 0;
    }

    @Override
    public int updatePassword(UpdateUserPasswordDTO updateUserPasswordDTO) {
        if (StrUtil.isEmpty(updateUserPasswordDTO.getUsername())
                || StrUtil.isEmpty(updateUserPasswordDTO.getOldPassword())
                || StrUtil.isEmpty(updateUserPasswordDTO.getNewPassword())) {
            return -1;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(updateUserPasswordDTO.getUsername());
        List<User> userList = userMapper.selectByExample(example);
        if (CollUtil.isEmpty(userList)) {
            return -2;
        }
        User user = userList.get(0);
        if (!passwordEncoder.matches(updateUserPasswordDTO.getOldPassword(), user.getPassword())) {
            return -3;
        }
        user.setPassword(passwordEncoder.encode(updateUserPasswordDTO.getNewPassword()));
        userMapper.updateByPrimaryKey(user);
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取用户信息
        User user = getUserByUsername(username);
        if (user != null) {
            List<Resource> resourceList = getResourceList(user.getId());
            log.info("用户可访问的资源信息" + resourceList);
            return new UserDetail(user, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public int getUserCount() {
        return userDAO.getUserCount();
    }
}
