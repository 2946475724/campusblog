package com.zs.campusblog.controller;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.TagDTO;
import com.zs.campusblog.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zs
 * @date 2020/2/14
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @ApiOperation(value = "获取标签列表")
    @GetMapping("/list")
    public Result getTagList() {

        return Result.success("");
    }

    @ApiOperation("根据标签id获取标签信息")
    @GetMapping("/{id}")
    public Result getTagById(@PathVariable Integer id) {
        TagDTO tag = tagService.getTagById(id);
        return Result.success(tag);
    }
}
