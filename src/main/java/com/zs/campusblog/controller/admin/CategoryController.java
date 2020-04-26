package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.vo.CategoryVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取不同分类的文章数")
    @GetMapping("/getArticleCountByCategory")
    public Result getArticleCountByCategory() {
        List<CategoryVO> categories = categoryService.getArticleCountByCategory();
        return Result.success(categories);
    }

}
