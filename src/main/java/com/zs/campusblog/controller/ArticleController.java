package com.zs.campusblog.controller;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.TagService;
import com.zs.campusblog.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 * 文章管理Controller
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

    @ApiOperation(value = "查询文章")
    @GetMapping(value = "/list")
    public Result<CommonPage<ArticleDTO>> getList(ArticleQueryParam articleQueryParam,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<ArticleDTO> articleList = articleService.list(articleQueryParam, pageSize, pageNum);
        CommonPage commonPage = new CommonPage<ArticleDTO>();
        return Result.success(CommonPage.restPage(articleList));
    }

    @ApiOperation(value = "添加文章")
    @PostMapping(value = "/add")
    public Result add(@RequestBody ArticleVO articleVO, BindingResult result) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        articleService.createOrUpdate(article);
        return Result.success(article);
    }

    @ApiOperation(value = "编辑文章")
    @PostMapping(value = "/edit/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ArticleVO articleVO, BindingResult result) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        article.setId(id);
        int count = articleService.createOrUpdate(article);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation(value = "根据id获取文章信息")
    @GetMapping(value = "/{id}")
    public Result getArticleInfoById(@PathVariable Integer id) {
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }

    @ApiOperation(value = "批量修改删除状态")
    @PostMapping(value = "/update/deleteStatus")
    public Result updateDeleteStatus(@RequestParam("ids") List<Integer> ids, @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = articleService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }


}
