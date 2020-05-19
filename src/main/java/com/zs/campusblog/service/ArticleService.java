package com.zs.campusblog.service;

import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.ArticleCollection;
import com.zs.campusblog.vo.ArticleVO;

import java.util.List;
import java.util.Map;

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
    List<ArticleDTO> list(ArticleVO articleVO, Integer pageSize, Integer pageNum);

    /**
     * 根据浏览量获取获取热门文章
     */
    List<Article> getHotArticle();

    /**
     * 获取推荐文章
     */
    List<ArticleDTO> getArticlesWithLevel();

    /**
     * 修改文章推荐
     */
    int updateArticleLevel(Integer id, Integer level);

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
     * 根据标签id获取文章列表
     */
    List<ArticleDTO> getArticlesByTagId(Integer id);

    /**
     * 批量删除文章
     */
    int updateDeleteStatus(List<Integer> ids, Integer deleteStatus);

    /**
     * 通过文章id删除文章
     */
    int deleteArticleById(Integer id);

    /**
     * 获取当前的文章数
     */
    int getArticleCount();

    /**
     * 获取一年内文章的贡献数
     */
    Map<String, Object> getArticleContributeCount();

    /**
     * 文章浏览量+1
     */
    int addArticleViewCount(Integer id);

    /**
     * 收藏文章或取消收藏
     */
    int collectionArticle(ArticleCollection articleCollection);

    /**
     * 查询收藏状态
     */
    Integer getCollectionStatus(Integer articleId, Integer userId);

    /**
     * 根据文章id增加文章评论数
     */
    Integer addComment(Integer articleId);

    /**
     * 通过用户id获取用户收藏的文章
     */
    List<ArticleDTO> getCollectArticleByUserId(Integer userId);

}
