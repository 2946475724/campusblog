package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/13
 */
public interface CategoryDAO {
    /**
     * 获取分类列表
     */
    List<Category> getCategoryList(@Param("keyword") String keyword);

    /**
     * 获取不同分类的文章数
     */
    List<CategoryVO> getArticleCountByCategory();

}
