package com.zs.campusblog.controller;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/13
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取所有分类信息")
    @GetMapping("/list")
    public Result getCategoryList() {
        List<Category> category = categoryService.getCategoryList();
        return Result.success(category);
    }
}
