package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Category;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface CategoryService {
    /**
     * 获取所有分类信息
     */
    List<Category> getCategoryList();
}
