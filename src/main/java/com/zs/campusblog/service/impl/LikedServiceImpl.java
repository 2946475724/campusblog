package com.zs.campusblog.service.impl;

import com.zs.campusblog.dao.ArticleDAO;
import com.zs.campusblog.dao.ArticleLikeDAO;
import com.zs.campusblog.mbg.mapper.ArticleLikeMapper;
import com.zs.campusblog.mbg.model.ArticleLike;
import com.zs.campusblog.mbg.model.ArticleLikeExample;
import com.zs.campusblog.service.LikedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/4/11
 */
@Slf4j
@Service
public class LikedServiceImpl implements LikedService {

    @Autowired
    ArticleLikeMapper articleLikeMapper;
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    ArticleLikeDAO articleLikeDAO;

    @Override
    public int like(ArticleLike articleLike) {
        ArticleLikeExample articleLikeExample = new ArticleLikeExample();
        ArticleLikeExample.Criteria criteria = articleLikeExample.createCriteria();
        criteria.andUserIdEqualTo(articleLike.getUserId());
        criteria.andArticleIdEqualTo(articleLike.getArticleId());
        List<ArticleLike> result = articleLikeMapper.selectByExample(articleLikeExample);
        System.out.println(result);
        if (result.isEmpty()) {
            articleLikeMapper.insertSelective(articleLike);
            articleDAO.incrementLikes(articleLike.getArticleId());
        } else {
            unlike(articleLike);
        }
        return 1;
    }

    @Override
    public int unlike(ArticleLike articleLike) {
        articleLikeDAO.updateStatus(articleLike);
        if (articleLike.getStatus() == 0) {
            articleDAO.decrementLikes(articleLike.getArticleId());
        } else {
            articleDAO.incrementLikes(articleLike.getArticleId());
        }
        return 1;
    }
}
