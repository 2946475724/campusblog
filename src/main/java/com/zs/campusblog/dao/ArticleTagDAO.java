package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.ArticleTag;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/29
 */
public interface ArticleTagDAO {
    int batchInsert(List<ArticleTag> articleTags);
}
