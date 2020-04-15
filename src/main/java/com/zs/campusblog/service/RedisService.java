package com.zs.campusblog.service;

/**
 * @author zs
 * @date 2019/12/29
 */
public interface RedisService {
    /**
     * 点赞，状态为1
     */
    void saveLiked2Red(Integer articleId, Integer userId);

    /**
     * 取消点赞，状态为0
     */

}
