package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dto.MenuNode;
import com.zs.campusblog.mbg.mapper.MenuMapper;
import com.zs.campusblog.mbg.model.Menu;
import com.zs.campusblog.mbg.model.MenuExample;
import com.zs.campusblog.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zs
 * @date 2020/1/4
 * 后台菜单管理Service实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public int create(Menu menu) {
        return 0;
    }

    @Override
    public int update(Integer id, Menu menu) {
        return 0;
    }

    @Override
    public Menu getItem(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Menu> list(Integer parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        MenuExample example = new MenuExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<MenuNode> treeList() {
        List<Menu> menuList = menuMapper.selectByExample(new MenuExample());
        List<MenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        System.out.println("========>"+result);
        return result;
    }


    @Override
    public int updateHidden(Integer id, Integer hidden) {
        return 0;
    }

    /**
     * 将Menu转化为MenuNode并设置children属性
     * @return
     */
    private MenuNode covertMenuNode(Menu menu, List<Menu> menuList) {
        MenuNode node = new MenuNode();
        BeanUtils.copyProperties(menu, node);
        List<MenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
