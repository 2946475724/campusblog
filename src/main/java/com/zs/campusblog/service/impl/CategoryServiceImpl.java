package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dao.CategoryDAO;
import com.zs.campusblog.mbg.mapper.CategoryMapper;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.mbg.model.CategoryExample;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Category> getCategoryList(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return categoryDAO.getCategoryList(keyword);
    }

    @Override
    public List<Category> getCategories() {
        return categoryMapper.selectByExample(new CategoryExample());
    }

    @Override
    public List<CategoryVO> getArticleCountByCategory() {
        return categoryDAO.getArticleCountByCategory();
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int addCategory(Category category) {
        category.setCreateTime(new Date());
        return categoryMapper.insertSelective(category);
    }


}
