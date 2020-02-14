package com.zs.campusblog.service;

import com.zs.campusblog.entity.Category;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface CategoryService extends BaseService<Category> {
    /**
     * 获取所有分类
     * @return
     */
    List<Category> getCategoryList();
}
