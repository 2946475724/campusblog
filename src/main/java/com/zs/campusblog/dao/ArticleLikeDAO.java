package com.zs.campusblog.dao;


import com.zs.campusblog.mbg.model.ArticleLike;

/**
 * @author zs
 * @date 2020/4/11
 */
public interface ArticleLikeDAO {
    /**
     * 更新点赞状态
     */
    int updateStatus(ArticleLike articleLike);
}
