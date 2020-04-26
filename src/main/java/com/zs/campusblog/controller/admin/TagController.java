package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.TagDTO;
import com.zs.campusblog.mbg.model.Tag;
import com.zs.campusblog.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result getTagList(@RequestParam(name = "keyword", required = false) String keyword,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Tag> tagList = tagService.getTagList(keyword.trim(), pageNum, pageSize);
        CommonPage commonPage = new CommonPage<Tag>();
        return Result.success(CommonPage.restPage(tagList));
    }

    @ApiOperation("根据标签id获取标签信息")
    @GetMapping("/{id}")
    public Result getTagById(@PathVariable Integer id) {
        TagDTO tag = tagService.getTagById(id);
        return Result.success(tag);
    }

    @ApiOperation("获取标签数")
    @GetMapping("/getTagCount")
    public Result getTagCount() {
        return Result.success(tagService.getTagCount());
    }
}
