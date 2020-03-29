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
}
