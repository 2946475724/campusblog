package com.zs.campusblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zs
 * @date 2020/3/9
 * 设置虚拟路径，访问绝对路径下资源
 */
@Configuration
public class UploadFileConfig implements WebMvcConfigurer {
    @Value("${image.upload.windowsPath}")
    private String baseFolderPath;

    @Value("${image.upload.linuxPath}")
    private String linuxBaseFolderPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            // 如果是Windows系统
            registry.addResourceHandler("/images/**").addResourceLocations("file:" + baseFolderPath);
        } else {
            // linux或mac系统
            registry.addResourceHandler("/images/**").addResourceLocations("file:" + linuxBaseFolderPath);
        }
    }
}
