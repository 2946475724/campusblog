package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.mbg.mapper.ArticleMapper;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.ArticleExample;
import com.zs.campusblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int create(ArticleDTO articleDTO) {

        return 0;
    }

    @Override
    public int update(Integer id, ArticleDTO articleDTO) {
        return 0;
    }

    @Override
    public List<Article> list(ArticleQueryParam articleQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(articleQueryParam.getKeyword())) {
            criteria.andTitleLike("%" + articleQueryParam.getKeyword() + "%");
        }
        return articleMapper.selectByExample(articleExample);
    }
}
