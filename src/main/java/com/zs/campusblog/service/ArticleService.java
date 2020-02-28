//package com.zs.campusblog.service;
//
//import com.zs.campusblog.entity.Article;
//import org.apache.ibatis.annotations.Options;
//
//import java.util.List;
//
///**
// * @author zs
// * @date 2020/1/9
// */
//public interface ArticleService extends BaseService<Article> {
//
//    /**
//     * 通过id获取文章信息
//     * @param id 文章id
//     * @return
//     */
//    Article getArticleById(Integer id);
//
//    /**
//     * 获取所有的文章信息
//     * @return
//     */
//    List<Article> getAllArticle();
//
//    /**
//     * 添加文章
//     * @param article 文章
//     */
//    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
//    int addArticle(Article article);
//
//    /**
//     * 更新文章
//     * @param article 文章
//     */
//    void updateArticleById(Article article);
//
//    /**
//     * 根据文章id删除文章
//     * @param ids 文章id
//     * @return
//     */
//    int deleteArticleById(Integer id);
//}
