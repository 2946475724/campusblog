package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.TagService;
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
    public Result<CommonPage<ArticleDTO>> getList(ArticleQueryParam articleQueryParam,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<ArticleDTO> articleList = articleService.list(articleQueryParam, pageSize, pageNum);
        CommonPage commonPage = new CommonPage<ArticleDTO>();
        return Result.success(CommonPage.restPage(articleList));
    }

    @ApiOperation(value = "根据id获取文章信息")
    @GetMapping(value = "/{id}")
    public Result getArticleInfoById(@PathVariable Integer id) {
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }


}