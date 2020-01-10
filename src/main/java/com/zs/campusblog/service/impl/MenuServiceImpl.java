package com.zs.campusblog.service.impl;

import com.zs.campusblog.entity.Menu;
import com.zs.campusblog.entity.User;
import com.zs.campusblog.mapper.MenuMapper;
import com.zs.campusblog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/4
 */
@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    @Override
    public List<Menu> menuTree() {
        return menuMapper.menuTree();
    }

    @Override
    public List<Integer> getMenusByRoleId(Integer roleId) {
        return menuMapper.getMenusByRoleId(roleId);
    }
}
