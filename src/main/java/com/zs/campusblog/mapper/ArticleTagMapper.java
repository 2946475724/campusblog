package com.zs.campusblog.mapper;

import com.zs.campusblog.entity.ArticleTag;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/14
 */
public interface ArticleTagMapper extends Mapper<ArticleTag> {
    int batchInsert(List<ArticleTag> articleTags);
}
