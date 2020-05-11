package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.TagService;
import com.zs.campusblog.service.UserService;
import com.zs.campusblog.vo.CategoryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zs
 * @date 2020/5/10
 */
@RestController
@RequestMapping("/admin/index")
public class AdminIndexController {
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取当前文章数")
    @GetMapping("/getArticleCount")
    public Result getArticleCount() {
        return Result.success(articleService.getArticleCount());
    }

    @ApiOperation(value = "获取一年内的文章贡献数", notes = "获取一年内的文章贡献度", response = String.class)
    @GetMapping(value = "/getArticleContributeCount")
    public Result<Map<String, Object>> getBlogContributeCount() {
        Map<String, Object> resultMap = articleService.getArticleContributeCount();
        return Result.success(resultMap);
    }

    @ApiOperation("获取标签数")
    @GetMapping("/getTagCount")
    public Result getTagCount() {
        return Result.success(tagService.getTagCount());
    }

    @ApiOperation("获取当前网站用户数")
    @GetMapping("/getUserCount")
    public Result getUserCount() {
        return Result.success(userService.getUserCount());
    }

    @ApiOperation("获取不同分类的文章数")
    @GetMapping("/getArticleCountByCategory")
    public Result getArticleCountByCategory() {
        List<CategoryVO> categories = categoryService.getArticleCountByCategory();
        return Result.success(categories);
    }

}
