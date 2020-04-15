package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.ArticleLike;

/**
 * 文章点赞
 */
public interface LikedService {
    /**
     * 点赞
     */
    int like(ArticleLike articleLike);

    /**
     * 取消点赞
     */
    int unlike(ArticleLike articleLike);
}
