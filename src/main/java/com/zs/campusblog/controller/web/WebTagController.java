package com.zs.campusblog.controller.web;

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
 * @date 2020/5/18
 * 前台标签控制器
 */
@RestController
@RequestMapping("/api/tag")
public class WebTagController {
    @Autowired
    TagService tagService;

    @ApiOperation("根据标签id获取标签信息")
    @PostMapping("/{id}")
    public Result getTagById(@PathVariable(name = "id") Integer id) {
        TagDTO tag = tagService.getTagById(id);
        return Result.success(tag);
    }

    @ApiOperation(value = "获取标签列表")
    @GetMapping(value = "/getTagList")
    public Result getTagList() {
        List<Tag> tagList = tagService.getTags();
        return Result.success(tagList);
    }

    @ApiOperation(value = "获取标签列表")
    @GetMapping("/list")
    public Result getTagList(@RequestParam(name = "keyword", required = false) String keyword,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Tag> tagList = tagService.getTagList(keyword.trim(), pageNum, pageSize);
        CommonPage commonPage = new CommonPage<Tag>();
        return Result.success(CommonPage.restPage(tagList));
    }

    @ApiOperation(value = "通过标签点击量获取标签列表")
    @GetMapping(value = "/getTagListByClickCount")
    public Result getTagListByClickCount() {
        List<Tag> tagList = tagService.getTagListByClickCount();
        return Result.success(tagList);
    }

    @ApiOperation("根据标签对应的文章数获取标签列表")
    @GetMapping("/getTagListWithHot")
    public Result getTagListWithHot() {
        List<TagDTO> tagDTOList = tagService.getTagListWithHot();
        return Result.success(tagDTOList);
    }

    @ApiOperation("根据创建时间获取标签列表")
    @GetMapping("/getTagListWithLatest")
    public Result getTagListWithLatest() {
        List<TagDTO> tagDTOList = tagService.getTagListWithLatest();
        return Result.success(tagDTOList);
    }

    @ApiOperation("添加标签点击量")
    @PostMapping("/{id}/count")
    public Result increaseClickCount(@PathVariable("id") Integer id) {
        int result = tagService.increaseClickCount(id);
        return Result.success("");
    }

}
