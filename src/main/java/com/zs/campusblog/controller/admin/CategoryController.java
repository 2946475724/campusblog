package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/13
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取所有分类信息")
    @GetMapping("/list")
    public Result getCategoryList(@RequestParam(name = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Category> category = categoryService.getCategoryList(keyword, pageNum, pageSize);
        return Result.success(CommonPage.restPage(category));
    }

    @ApiOperation(value = "获取所有分类信息不带参数")
    @GetMapping("/listAll")
    public Result getCategoryList() {
        List<Category> category = categoryService.getCategories();
        return Result.success(category);
    }

    @ApiOperation(value = "编辑分类")
    @PostMapping("/update")
    public Result updateCategory(@RequestBody Category category) {
        int result = categoryService.updateCategory(category);
        if (result > 0) {
            return Result.success("", "更新成功");
        }
        return Result.failed();
    }

    @ApiOperation(value = "添加新的分类")
    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category) {
        int result = categoryService.addCategory(category);
        if (result > 0) {
            return Result.success("", "添加成功");
        }
        return Result.failed();
    }





}
