package com.zs.campusblog.dao;

import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.mbg.model.Article;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/29
 */
public interface ArticleDAO {

    List<ArticleDTO> getArticleList(ArticleQueryParam articleQueryParam);

    /**
     * 根据浏览量获取热门文章
     */
    List<Article> getHotArticle();

    /**
     * 根据文章id获取文章信息
     */
    ArticleDTO getArticleById(Integer id);

    /**
     * 根据用户id获取文章列表
     */
    List<ArticleDTO> getArticlesByUserId(Integer id);

    /**
     * 根据分类id获取文章列表
     */
    List<ArticleDTO> getArticlesByCategoryId(Integer categoryId);

}
