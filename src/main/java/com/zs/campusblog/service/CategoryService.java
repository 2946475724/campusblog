package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.vo.CategoryVO;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface CategoryService {
    /**
     * 获取所有分类信息
     */
    List<Category> getCategoryList(String keyword, Integer pageNum, Integer pageSize);

    List<Category> getCategories();

    /**
     * 获取不同分类的文章数
     */
    List<CategoryVO> getArticleCountByCategory();
}
