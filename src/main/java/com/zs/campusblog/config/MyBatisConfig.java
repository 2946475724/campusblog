package com.zs.campusblog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zs
 * @date 2020/2/27
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.zs.campusblog.mbg.mapper","com.zs.campusblog.dao"})
public class MyBatisConfig {
}
