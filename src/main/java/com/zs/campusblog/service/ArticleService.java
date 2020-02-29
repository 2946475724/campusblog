package com.zs.campusblog.service;

import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 * 文章管理Service
 */
public interface ArticleService {
    /**
     * 创建文章
     */
    int create(ArticleDTO articleDTO);

    /**
     * 根据文章编号获取更新信息
     */


    /**
     * 更新文章
     */
    int update(Integer id, ArticleDTO articleDTO);

    /**
     * 分页查询文章
     */
    List<ArticleDTO> list(ArticleQueryParam articleQueryParam, Integer pageSize, Integer pageNum);

}
