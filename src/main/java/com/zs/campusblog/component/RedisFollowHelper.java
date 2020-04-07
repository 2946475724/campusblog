package com.zs.campusblog.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zs
 * @date 2020/4/6
 */
@Component
public class RedisFollowHelper {

    private static final String FOLLOW = "FOLLOW:%s";
    private static final String FANS = "FANS:%s";

    @Autowired
    StringRedisTemplate redisTemplate;

    public void follow(Integer userId, Integer followingId) {
        String followingKey = String.format(FOLLOW, userId);
        String fansKey = String.format(FANS, followingId);
        Long createTime = System.currentTimeMillis();
        redisTemplate.opsForZSet().add(followingKey, String.valueOf(followingId), createTime);
        redisTemplate.opsForZSet().add(fansKey, String.valueOf(userId), createTime);
    }

    public void unFollow(Integer userId, Integer followingId) {
        String followingKey = String.format(FOLLOW, userId);
        String fansKey = String.format(FANS, followingId);
        redisTemplate.opsForZSet().remove(followingKey, String.valueOf(followingId));
        redisTemplate.opsForZSet().remove(fansKey, String.valueOf(userId));
    }

    /**
     * 获取用户锁关注的人的id
     */
    public Set<String> getFollowings(Integer userId) {
        String followingKey = String.format(FOLLOW, userId);
        Set<String> follows = redisTemplate.opsForZSet().range(followingKey, 0, -1);
        return follows;
    }

    /**
     * 获取关注数
     */
    public Long getFollowNum(Integer userId) {
        String followingKey = String.format(FOLLOW, userId);
        return redisTemplate.opsForZSet().zCard(followingKey);
    }

    /**
     * 获取粉丝列表
     */
    public Set<String> getFans(Integer followingId) {
        String fansKey = String.format(FANS, followingId);
        Set<String> fans = redisTemplate.opsForZSet().range(fansKey, 0, -1);
        return fans;
    }

    /**
     * 获取粉丝数
     */
    public Long getFansNum(Integer followingId) {
        String fansKey = String.format(FANS, followingId);
        return redisTemplate.opsForZSet().zCard(fansKey);
    }

}
