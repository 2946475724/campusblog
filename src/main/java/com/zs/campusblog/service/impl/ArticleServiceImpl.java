//package com.zs.campusblog.service.impl;
//
//import com.zs.campusblog.entity.Article;
//import com.zs.campusblog.entity.ArticleTag;
//import com.zs.campusblog.entity.Tag;
//import com.zs.campusblog.mapper.ArticleMapper;
//import com.zs.campusblog.mapper.ArticleTagMapper;
//import com.zs.campusblog.mapper.CategoryMapper;
//import com.zs.campusblog.mapper.TagMapper;
//import com.zs.campusblog.service.ArticleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author zs
// * @date 2020/1/9
// */
//@Service
//public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
//
//    @Autowired
//    ArticleMapper articleMapper;
//    @Autowired
//    CategoryMapper categoryMapper;
//    @Autowired
//    TagMapper tagMapper;
//    @Autowired
//    ArticleTagMapper articleTagMapper;
//
//    @Override
//    public Article getArticleById(Integer id) {
//        return articleMapper.getArticleById(id);
//    }
//
//    @Override
//    public List<Article> getAllArticle() {
//        return articleMapper.getAllArticle();
//    }
//
//    @Override
//    public int addArticle(Article article) {
//        //前端接受的tags
//        String[] tags = article.getTags().split(",");
//        //保存文章
//        Timestamp createTime = new Timestamp(System.currentTimeMillis());
//        article.setCreateTime(createTime);
//        if (articleMapper.insertSelective(article) > 0) {
//            List<Tag> newTags = new ArrayList<>();
//            List<Tag> allTags = new ArrayList<>();
//            for (int i = 0; i < tags.length; i++) {
//                Tag tag = tagMapper.selectByTagName(tags[i]);
//                if (tag == null) {
//                    //不存在就新增
//                    Tag tempTag = new Tag();
//                    tempTag.setName(tags[i]);
//                    tempTag.setCreateTime(createTime);
//                    newTags.add(tempTag);
//                } else {
//                    allTags.add(tag);
//                }
//            }
//            //新增标签数据
//            if (!CollectionUtils.isEmpty(newTags)) {
//                tagMapper.batchInsertTag(newTags);
//            }
//            List<ArticleTag> articleTags = new ArrayList<>();
//            //新增关系数据
//            allTags.addAll(newTags);
//            for (Tag tag : allTags) {
//                ArticleTag articleTag = new ArticleTag();
//                articleTag.setArticleId(article.getId());
//                articleTag.setTagId(tag.getId());
//                articleTags.add(articleTag);
//            }
//            articleTagMapper.batchInsert(articleTags);
//        }
//        return 0;
//    }
//
//    @Override
//    public void updateArticleById(Article article) {
//
//    }
//
//    @Override
//    public int deleteArticleById(Integer id) {
//        return articleMapper.deleteArticleById(id);
//    }
//
//}
