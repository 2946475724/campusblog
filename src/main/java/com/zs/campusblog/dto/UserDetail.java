package com.zs.campusblog.dto;

import com.zs.campusblog.mbg.model.Permission;
import com.zs.campusblog.mbg.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zs
 * @date 2020/2/27
 * SpringSecurity需要的用户详情
 */
public class UserDetail implements UserDetails{
    private User user;
    private List<Permission> permissionList;

    public UserDetail(User user, List<Permission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(1);
    }
}
