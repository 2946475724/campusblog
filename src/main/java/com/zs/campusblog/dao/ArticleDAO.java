package com.zs.campusblog.dao;

import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.ArticleCollection;
import com.zs.campusblog.vo.ArticleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("SELECT * from article WHERE delete_status = 1 ORDER BY views DESC limit 0,10")
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
     * 根据标签id获取文章列表
     */
    List<ArticleDTO> getArticlesByTagId(Integer id);

    /**
     * 根据分类id获取文章列表
     */
    List<ArticleDTO> getArticlesByCategoryId(Integer categoryId);

    /**
     * 通过推荐获取文章
     */
    List<ArticleDTO> getArticlesWithLevel();

    /**
     * 更新文章表点赞数+1
     */
    int incrementLikes(Integer articleId);

    /**
     * 更新文章表点赞数-1
     */
    int decrementLikes(Integer articleId);

    /**
     * 查询文章点赞状态
     */
    @Select("select al.`status` from article_like al where al.article_id = #{articleId} and al.user_id = #{userId}")
    Integer getLikeStatus(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

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

    /**
     * 文章浏览量+1
     */
    @Update("update article set views = views + 1 where id = #{id}")
    int addArticleViewCount(@Param("id") Integer id);

    /**
     * 收藏文章
     */
    @Insert("insert into article_collection(article_id, user_id, `status`, create_time) values(#{articleCollection.articleId}, #{articleCollection.userId}, 1, CURRENT_TIMESTAMP)")
    int collectionArticle(@Param("articleCollection") ArticleCollection articleCollection);

    /**
     * 取消收藏文章
     */
    @Update("update article_collection set status = #{articleCollection.status} where article_id = #{articleCollection.articleId} and user_id = #{articleCollection.userId}")
    int unCollectionArticle(@Param("articleCollection") ArticleCollection articleCollection);

    /**
     * 根据文章id和用户id获取收藏状态
     */
    @Select("select * from article_collection where article_id = #{articleCollection.articleId} and user_id = #{articleCollection.userId}")
    ArticleCollection getArticleCollection(@Param("articleCollection") ArticleCollection articleCollection);

    /**
     * 更新文章表收藏数+1
     */
    @Update("update article set collections = collections + 1 where id = #{articleId}")
    int incrementCollections(@Param("articleId") Integer articleId);

    /**
     * 更新文章表收藏数-1
     */
    @Update("update article set collections = collections - 1 where id = #{articleId}")
    int decrementCollections(@Param("articleId")Integer articleId);

    /**
     * 查询文章收藏状态
     */
    @Select("select ac.`status` from article_collection ac where ac.article_id = #{articleId} and ac.user_id = #{userId}")
    Integer getCollectionStatus(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    /**
     * 文章评论数+1
     */
    @Update("update article set comment = comment + 1 where id = #{articleId}")
    Integer incrementComment(@Param("articleId") Integer articleId);

    /**
     * 通过用户id获取用户收藏的文章
     */
    @Select("SELECT * FROM article WHERE id IN ( SELECT article_id FROM article_collection WHERE user_id = #{userId} AND status = 1 )")
    List<ArticleDTO> getCollectArticleByUserId(@Param("userId") Integer userId);
}
