package com.zs.campusblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.campusblog.entity.*;
import com.zs.campusblog.entity.vo.ArticleVO;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.TagService;
import com.zs.campusblog.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;

    @ApiOperation(value = "根据文章id获取文章信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Article> getArticleById(@PathVariable Integer id) {
        Article article = articleService.getArticleById(id);
        return ResultUtil.success(article);
    }

    @ApiOperation(value = "获取所有文章")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Article> getAllArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleService.getAllArticle();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        int totalPages = pageInfo.getPages();
        int pageNum1 = pageInfo.getPageNum();
        int total = (int) pageInfo.getTotal();
        return ResultUtil.success("获取成功", articles, new PageBaseInfo(pageNum1, totalPages, total));
    }

    @ApiOperation(value = "添加文章")
    @PostMapping(value = "/add")
    public Result<Article> addArticle(ArticleVO articleVO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        articleService.addArticle(article);
        return ResultUtil.success("添加成功", null);
    }

    @ApiOperation(value = "根据文章id删除文章")
    @DeleteMapping(value = "/deleteArticleById")
    public Result<Article> deleteArticleById(@RequestParam(value = "id") Integer id) {
        int result = articleService.deleteArticleById(id);
        if(result > 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("删除失败");
        }
    }

    @ApiOperation(value = "获取所有文章分类信息")
    @GetMapping(value = "/categories")
    public Result<Article> getCategoryList() {
        List<Category> categories = categoryService.getCategoryList();
        return ResultUtil.success(categories);
    }

    @ApiOperation(value = "获取所有文章标签信息")
    @GetMapping(value = "/tags")
    public Result<Article> getTagList() {
        List<Tag> tags = tagService.selectAll();
        return ResultUtil.success(tags);
    }

    @ApiOperation(value = "编辑文章")
    @PostMapping("/edit")
    public Result<Article> editArticle(ArticleVO articleVO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        articleService.updateAll(article);
        return ResultUtil.success("更新成功", "");
    }
}
