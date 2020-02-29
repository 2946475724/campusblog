package com.zs.campusblog.service;

import com.zs.campusblog.dto.MenuNode;
import com.zs.campusblog.mbg.model.Menu;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/4
 * 后台菜单管理Service
 */
public interface MenuService {
    /**
     * 创建后台菜单
     */
    int create(Menu menu);

    /**
     * 修改后台菜单
     */
    int update(Integer id, Menu menu);

    /**
     * 根据ID获取菜单详情
     */
    Menu getItem(Integer id);

    /**
     * 根据ID删除菜单
     */
    int delete(Integer id);

    /**
     * 分页查询后台菜单
     */
    List<Menu> list(Integer parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单
     */
    List<MenuNode> treeList();

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Integer id, Integer hidden);


}
