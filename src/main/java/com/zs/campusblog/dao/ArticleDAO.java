package com.zs.campusblog.dao;

import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author zs
 * @date 2020/2/29
 */
public interface ArticleDAO {

    /**
     * 获取文章列表
     */
    List<ArticleDTO> getArticleList(ArticleVO articleVO);

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

    /**
     * 更新文章表点赞数+1
     */
    int incrementLikes(Integer articleId);

    /**
     * 更新文章表点赞数-1
     */
    int decrementLikes(Integer articleId);

    /**
     * 删除文章，即更新文章状态
     */
    int deleteArticleById(Integer id);

    /**
     * 获取当前的文章数
     */
    int getArticleCount();

    /**
     * 获取一年内的文章贡献数
     */
    @Select("SELECT DISTINCT DATE_FORMAT(create_time, '%Y-%m-%d') DATE, COUNT(id) COUNT FROM article WHERE 1=1 AND create_time >= #{startTime} AND create_time < #{endTime} GROUP BY DATE_FORMAT(create_time, '%Y-%m-%d')")
    List<Map<String, Object>> getArticleContributeCount(@Param("startTime") String startTime, @Param("endTime") String endTime);


}
