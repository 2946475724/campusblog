package com.zs.campusblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zs
 * @date 2019/12/29
 */
@SpringBootApplication
public class CampusblogApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CampusblogApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CampusblogApplication.class, args);
    }

}
