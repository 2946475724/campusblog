package com.zs.campusblog.mapper;

import com.zs.campusblog.entity.Menu;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/4
 */
public interface MenuMapper {

    /**
     * 通过主键进行删除
     * @param id 主键id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     * @param record 菜单记录
     * @return
     */
    int insert(Menu record);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getAllMenu();

    /**
     * 根据角色获取所有菜单
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 根据用户id获取菜单
     * @param userId 用户id
     * @return
     */
    List<Menu> getMenusByUserId(Integer userId);

    /**
     * 菜单树
     * @return
     */
    List<Menu> menuTree();

    /**
     * 根据角色id获取菜单
     * @param rid
     * @return
     */
    List<Integer> getMenusByRoleId(Integer rid);
}
