package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Category;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface CategoryService {
    /**
     * 分页查询文章
     */
    List<Category> list(Category category, Integer pageSize, Integer pageNum);
}
