package com.zs.campusblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.zs.campusblog.entity.Result;
import com.zs.campusblog.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author zs
 * @date 2020/1/9
 */
@RestController
public class UploadController {

    @Value("${campusblog.upload.path}")
    private String baseFolderPath;

    @ApiOperation(value = "图片上传")
    @PostMapping("/upload")
    public Result upload(HttpServletRequest request, MultipartFile image) {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd");
        String filePath = sdf.format(new Date());

        File baseFolder = new File(baseFolderPath + filePath);
        if(!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        StringBuffer url = new StringBuffer();
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append("/")
                .append(filePath);
        String imgName = UUID.randomUUID().toString().replace("_", "") + "_" + image.getOriginalFilename().replaceAll(" ", "");
        System.out.println(imgName);
        try {
            File dest = new File(baseFolder, imgName);
            System.out.println(dest);
            image.transferTo(dest);
            url.append("/").append(imgName);
            System.out.println(url);

            JSONObject object = new JSONObject();
            object.put("url", url);

            return ResultUtil.success("上传成功！");
        }catch (IOException e) {
            return ResultUtil.error("文件上传错误");
        }
    }
}
