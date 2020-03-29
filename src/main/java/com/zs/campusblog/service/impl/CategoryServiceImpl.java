package com.zs.campusblog.service.impl;

import com.zs.campusblog.dao.CategoryDAO;
import com.zs.campusblog.mbg.mapper.CategoryMapper;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }
}
