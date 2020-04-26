package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.TagDTO;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.mbg.model.Tag;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.TagService;
import com.zs.campusblog.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/5
 * 前台首页控制器
 */
@RestController
@RequestMapping("/api/index")
public class IndexController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;

    @ApiOperation(value = "查询文章")
    @GetMapping(value = "/list")
    public Result<CommonPage<ArticleDTO>> getList(ArticleVO articleVO,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<ArticleDTO> articleList = articleService.list(articleVO, pageSize, pageNum);
        CommonPage commonPage = new CommonPage<ArticleDTO>();
        return Result.success(CommonPage.restPage(articleList));
    }

    @ApiOperation(value = "根据id获取文章信息")
    @GetMapping(value = "/{id}")
    public Result getArticleInfoById(@PathVariable Integer id) {
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }

    @ApiOperation(value = "获取热门文章")
    @GetMapping(value = "/getHotArticle")
    public Result getHotArticle() {
        List<Article> hotArticles = articleService.getHotArticle();
        return Result.success(hotArticles);
    }

    @ApiOperation(value = "获取标签列表")
    @GetMapping(value = "/getTagList")
    public Result getTagList() {
        List<Tag> tagList = tagService.getTags();
        return Result.success(tagList);
    }

    @ApiOperation("根据标签id获取标签信息")
    @GetMapping("/tag")
    public Result getTagById(@RequestParam(name = "tagId") Integer id) {
        TagDTO tag = tagService.getTagById(id);
        return Result.success(tag);
    }

    @ApiOperation("根据分类id获取分类信息")
    @GetMapping("/category")
    public Result getCategoryById(@RequestParam(name = "categoryId") Integer id) {
        List<ArticleDTO> articleList = articleService.getArticlesByCategoryId(id);
        return Result.success(articleList);
    }

    @ApiOperation("获取文章分类列表")
    @GetMapping("/category/list")
    public Result getCategoryList(@RequestParam(name = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Category> category = categoryService.getCategoryList(keyword, pageNum, pageSize);
        return Result.success(CommonPage.restPage(category));
    }


}
