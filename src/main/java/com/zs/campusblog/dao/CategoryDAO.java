package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Category;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/13
 */
public interface CategoryDAO {
    List<Category> getCategoryList();
}
