package com.zs.campusblog.service;

import com.zs.campusblog.entity.Article;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */
public interface ArticleService extends IService<Article>{

    /**
     * 通过id获取文章信息
     * @param id 文章id
     * @return
     */
    Article getArticleById(Integer id);

    /**
     * 获取所有的文章信息
     * @return
     */
    List<Article> getAllArticle();

    /**
     * 根据文章id删除文章
     * @param ids 文章id
     * @return
     */
    int deleteArticleById(Integer id);
}
