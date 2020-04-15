package com.zs.campusblog.service;

import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.mbg.model.Article;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 * 文章管理Service
 */
public interface ArticleService {
    /**
     * 创建或更新文章
     */
    int createOrUpdate(Article article);

    /**
     * 分页查询文章
     */
    List<ArticleDTO> list(ArticleQueryParam articleQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 根据浏览量获取获取热门文章
     */
    List<Article> getHotArticle();

    /**
     * 根据id获取文章
     */
    ArticleDTO getArticleById(Integer id);

    /**
     * 根据用户id获取文章列表
     */
    List<ArticleDTO> getArticlesByUserId(Integer id);

    /**
     * 根据分类id获取文章列表
     */
    List<ArticleDTO> getArticlesByCategoryId(Integer id);

    /**
     * 批量删除文章
     */
    int updateDeleteStatus(List<Integer> ids, Integer deleteStatus);

    /**
     * 通过文章id删除文章
     */
    int deleteArticleById(Integer id);

}
