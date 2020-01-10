package com.zs.campusblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zs
 * @date 2019/12/29
 */
@MapperScan("com.zs.campusblog.mapper")
@EnableCaching
@SpringBootApplication
public class CampusblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusblogApplication.class, args);
    }

}
