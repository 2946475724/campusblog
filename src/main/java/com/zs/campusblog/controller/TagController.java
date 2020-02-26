package com.zs.campusblog.controller;

import com.zs.campusblog.entity.Result;
import com.zs.campusblog.entity.Tag;
import com.zs.campusblog.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zs
 * @date 2020/2/14
 */
@RestController("/tags")
public class TagController {

    @ApiOperation(value = "获取所有")
    @GetMapping("/list")
    public Result<Tag> getTags() {

        return ResultUtil.success();
    }
}
