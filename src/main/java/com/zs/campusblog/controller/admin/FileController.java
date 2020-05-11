package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zs
 * @date 2020/1/9
 * 文件上传下载接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${image.upload.windowsPath}")
    private String baseFolderPath;

    @ApiOperation(value = "图片上传")
    @PostMapping("/uploadImage")
    public Result upload(HttpServletRequest request, MultipartFile image) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filePath = sdf.format(new Date());

        File baseFolder = new File(baseFolderPath + filePath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        StringBuffer url = new StringBuffer();
//        url.append(request.getScheme())
//                .append("://")
//                .append(request.getServerName())
//                .append(":")
//                .append(request.getServerPort())
//                .append(request.getContextPath())
//                .append("/images/")
//                .append(filePath);
        System.out.println(image);
        String imgName = UUID.randomUUID().toString().replace("_", "") + "_" + image.getOriginalFilename().replaceAll(" ", "");
        System.out.println(imgName);
        try {
            File dest = new File(baseFolder, imgName);
            System.out.println(dest);
            image.transferTo(dest);
            url.append("/images/")
                    .append(filePath)
                    .append("/")
                    .append(imgName);
            System.out.println(url);
            Map<String, StringBuffer> urlMap = new HashMap<>();
            urlMap.put("url", url);
            return Result.success(urlMap, "上传成功");
        } catch (IOException e) {
            return Result.failed("文件上传错误");
        }
    }
}
