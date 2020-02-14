package com.zs.campusblog.mapper;

import com.zs.campusblog.entity.Article;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */
public interface ArticleMapper extends Mapper<Article> {

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
     * 获取文章总数
     * @return
     */
    int getArticleCount();

    /**
     * 获取文章列表
     * @return
     */
    List<Article> getArticleList();

    /**
     * 添加文章
     * @param article 文章
     */
    void addArticle(Article article);

    /**
     * 根据文章id删除文章
     * @param ids 文章id
     * @return
     */
    int deleteArticleById(Integer id);
}
