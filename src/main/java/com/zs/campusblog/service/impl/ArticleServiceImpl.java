package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dao.ArticleDAO;
import com.zs.campusblog.dao.ArticleTagDAO;
import com.zs.campusblog.dao.TagDAO;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.mbg.mapper.ArticleMapper;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.ArticleExample;
import com.zs.campusblog.mbg.model.ArticleTag;
import com.zs.campusblog.mbg.model.Tag;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.util.DateUtil;
import com.zs.campusblog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author zs
 * @date 2020/1/9
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagDAO tagDAO;
    @Autowired
    private ArticleTagDAO articleTagDAO;
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public int createOrUpdate(Article article) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        if (article.getId() == null) {
            //保存文章
            article.setCreateTime(currentTime);
            articleMapper.insertSelective(article);
        } else {
            //编辑文章
            article.setCreateTime(currentTime);
            articleMapper.updateByPrimaryKeySelective(article);
        }

        if (article.getTags() != null) {
            //前端接收的tags
            String[] tags = article.getTags().split(",");
            List<Tag> newTags = new ArrayList<>();
            List<Tag> allTags = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                Tag tag = tagDAO.selectByTagName(tags[i]);
                if (tag == null) {
                    //不存在就新增
                    Tag tempTag = new Tag();
                    tempTag.setName(tags[i]);
                    tempTag.setCreateTime(currentTime);
                    newTags.add(tempTag);
                } else {
                    allTags.add(tag);
                }
            }
            //新增标签数据
            if (!CollectionUtils.isEmpty(newTags)) {
                tagDAO.batchInsertTag(newTags);
            }
            List<ArticleTag> articleTags = new ArrayList<>();
            //新增关系数据
            allTags.addAll(newTags);
            for (Tag tag : allTags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(tag.getId());
                articleTags.add(articleTag);
            }
            articleTagDAO.batchInsert(articleTags);
        }
        return 1;
    }


    @Override
    public List<ArticleDTO> list(ArticleVO articleVO, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return articleDAO.getArticleList(articleVO);
    }

    @Override
    public List<Article> getHotArticle() {
        return articleDAO.getHotArticle();
    }

    @Override
    public ArticleDTO getArticleById(Integer id) {
        ArticleDTO article = articleDAO.getArticleById(id);
        return article;
    }

    @Override
    public List<ArticleDTO> getArticlesByUserId(Integer id) {
        return articleDAO.getArticlesByUserId(id);
    }

    @Override
    public List<ArticleDTO> getArticlesByCategoryId(Integer id) {
        return articleDAO.getArticlesByCategoryId(id);
    }

    @Override
    public int updateDeleteStatus(List<Integer> ids, Integer deleteStatus) {
        Article article = new Article();
        article.setDeleteStatus(deleteStatus);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andIdIn(ids);
        return articleMapper.updateByExampleSelective(article, articleExample);
    }

    @Override
    public int deleteArticleById(Integer id) {
        return articleDAO.deleteArticleById(id);
    }

    @Override
    public int getArticleCount() {
        return articleDAO.getArticleCount();
    }

    @Override
    public Map<String, Object> getArticleContributeCount() {
        // 获取今天结束时间
        String endTime = DateUtil.getNowTime();
        // 获取365天前的日期
        Date temp = DateUtil.getDate(endTime, -365);
        String startTime = DateUtil.dateTimeToStr(temp);
        List<Map<String, Object>> articleContributeMap = articleDAO.getArticleContributeCount(startTime, endTime);
        List<String> dateList = DateUtil.getDayBetweenDates(startTime, endTime);
        Map<String, Object> dateMap = new HashMap<>();
        for (Map<String, Object> itemMap : articleContributeMap) {
            dateMap.put(itemMap.get("DATE").toString(), itemMap.get("COUNT"));
        }
        List<List<Object>> resultList = new ArrayList<>();
        for (String item : dateList) {
            Integer count = 0;
            if (dateMap.get(item) != null) {
                count = Integer.valueOf(dateMap.get(item).toString());
            }
            List<Object> objectList = new ArrayList<>();
            objectList.add(item);
            objectList.add(count);
            resultList.add(objectList);
        }Map<String, Object> resultMap = new HashMap<>();
        List<String> contributeDateList = new ArrayList<>();
        contributeDateList.add(startTime);
        contributeDateList.add(endTime);
        resultMap.put("contributeDate" , contributeDateList);
        resultMap.put("articleContributeCount" , resultList);
        return resultMap;
    }


}
