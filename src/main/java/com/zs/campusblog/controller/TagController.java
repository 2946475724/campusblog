package com.zs.campusblog.controller;

import com.zs.campusblog.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zs
 * @date 2020/2/14
 */
@RestController
@RequestMapping("/tag")
public class TagController {


    @ApiOperation(value = "获取标签列表")
    @GetMapping("/list")
    public Result getTagList() {

        return Result.success("");
    }
}
