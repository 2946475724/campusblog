package com.zs.campusblog.controller;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.ArticleLike;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CategoryService;
import com.zs.campusblog.service.LikedService;
import com.zs.campusblog.service.TagService;
import com.zs.campusblog.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    LikedService likedService;

    @ApiOperation(value = "查询文章")
    @GetMapping(value = "/list")
    public Result<CommonPage<ArticleDTO>> getList(ArticleQueryParam articleQueryParam,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        System.out.println(articleQueryParam);
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
        ArticleDTO article = articleService.getArticleById(id);
        return Result.success(article);
    }

    @ApiOperation("根据用户id获取文章列表")
    @GetMapping("/user")
    public Result getArticlesByUserId(@RequestParam(name = "userId") Integer id) {
        List<ArticleDTO> articles = articleService.getArticlesByUserId(id);
        return Result.success(articles);
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

    @ApiOperation("文章点赞")
    @PostMapping("/like")
    public Result like(@RequestBody ArticleLike articleLike) {
        int result = likedService.like(articleLike);
        if (result > 0 && articleLike.getStatus() == 1) {
            return Result.success("", "点赞成功");
        } else if (result > 0 && articleLike.getStatus() == 0) {
            return Result.success("", "取消点赞成功");
        } else {
            return Result.failed("点赞失败");
        }
    }

    @ApiOperation("根据文章id删除文章")
    @PostMapping("/delete")
    public Result deleteArticleById(@RequestParam("id") Integer id) {
        int result = articleService.deleteArticleById(id);
        if (result > 0) {
            return Result.success(null,"删除成功");
        } else {
            return Result.failed("删除失败");
        }
    }


}
