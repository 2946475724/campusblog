package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.Category;
import com.zs.campusblog.mbg.model.Link;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.LinkService;
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
public class WebIndexController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    LinkService linkService;

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

    @ApiOperation("根据分类id获取分类信息")
    @GetMapping("/category")
    public Result getCategoryById(@RequestParam(name = "categoryId") Integer id) {
        List<ArticleDTO> articleList = articleService.getArticlesByCategoryId(id);
        return Result.success(articleList);
    }

    @ApiOperation(value = "分页获取所有分类信息")
    @GetMapping("/category/list")
    public Result getCategoryList(@RequestParam(name = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Category> category = categoryService.getCategoryList(keyword, pageNum, pageSize);
        return Result.success(CommonPage.restPage(category));
    }

    @ApiOperation(value = "获取所有分类信息")
    @GetMapping("/category/listAll")
    public Result getCategories() {
        List<Category> categoryList = categoryService.getCategories();
        return Result.success(categoryList);
    }

    @ApiOperation("获取友情链接")
    @GetMapping("/link/list")
    public Result getLink() {
        List<Link> linkList = linkService.getLinkListBySort();
        return Result.success(linkList);
    }



}
