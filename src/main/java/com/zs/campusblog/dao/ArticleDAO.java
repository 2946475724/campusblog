package com.zs.campusblog.dao;

import com.zs.campusblog.dto.ArticleDTO;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/29
 */
public interface ArticleDAO {

    List<ArticleDTO> getArticleList();
}
