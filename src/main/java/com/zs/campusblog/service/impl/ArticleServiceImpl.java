package com.zs.campusblog.service.impl;

import com.zs.campusblog.entity.Article;
import com.zs.campusblog.mapper.ArticleMapper;
import com.zs.campusblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */
@Service
public class ArticleServiceImpl extends IServiceImpl<Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    @Override
    public int deleteArticleById(Integer id) {
        return articleMapper.deleteArticleById(id);
    }
}
