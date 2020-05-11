package com.zs.campusblog.service.impl;

import com.zs.campusblog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zs
 * @date 2020/4/6
 * redis操作Service的实现类
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Override
//    public void set(String key, String value) {
//        stringRedisTemplate.opsForSet().add(key, value);
//    }
//
//    @Override
//    public String get(String key) {
//        return stringRedisTemplate.opsForSet().get(key);
//    }
//
//    @Override
//    public void remove(String key) {
//        stringRedisTemplate.delete(key);
//    }
}
