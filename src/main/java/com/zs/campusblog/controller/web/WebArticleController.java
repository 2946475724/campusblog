package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.common.aop.LogAop;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.ArticleCollection;
import com.zs.campusblog.mbg.model.ArticleLike;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.LikedService;
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
 * @date 2020/3/9
 * 前台文章先关控制器
 */
@RestController
@RequestMapping("/api/article")
public class WebArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    LikedService likedService;
    @Autowired
    TagService tagService;

    @LogAop("添加文章")
    @ApiOperation(value = "添加文章")
    @PostMapping(value = "/add")
    public Result add(@RequestBody ArticleVO articleVO, BindingResult result) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        articleService.createOrUpdate(article);
        return Result.success(article);
    }

    @ApiOperation("文章点赞或取消点赞")
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

    @ApiOperation("查询点赞状态")
    @PostMapping("/like/status")
    public Result getLikeStatus(Integer articleId, Integer userId) {
        Integer result = likedService.getLikeStatus(articleId, userId);
        return Result.success(result);
    }

    @ApiOperation("增加文章浏览量")
    @PostMapping("/{id}/view")
    public Result addArticleViewCount(@PathVariable Integer id) {
        int result = articleService.addArticleViewCount(id);
        return Result.success("", "文章浏览量+1");
    }

    @ApiOperation("根据标签id获取文章列表")
    @GetMapping("/getArticlesByTagId")
    public Result getArticlesByTagId(@RequestParam(name = "tagId") Integer id) {
        List<ArticleDTO> articles = articleService.getArticlesByTagId(id);
        return Result.success(articles);
    }

    @ApiOperation("获取推荐文章")
    @GetMapping("/recommended")
    public Result getArticlesWithLevel(Integer id) {
        List<ArticleDTO> articles = articleService.getArticlesWithLevel();
        return Result.success(articles);
    }

    @LogAop("根据文章id删除文章")
    @ApiOperation("根据文章id删除文章")
    @PostMapping("/delete")
    public Result deleteArticleById(@RequestParam("id") Integer id) {
        int result = articleService.deleteArticleById(id);
        if (result > 0) {
            return Result.success(null, "删除成功");
        } else {
            return Result.failed("删除失败");
        }
    }

    @ApiOperation("收藏文章或取消收藏")
    @PostMapping("/collection")
    public Result collectionArticle(@RequestBody ArticleCollection articleCollection) {
        int result = articleService.collectionArticle(articleCollection);
        if (result > 0 && articleCollection.getStatus() == 1) {
            return Result.success("", "收藏成功");
        } else if (result > 0 && articleCollection.getStatus() == 0) {
            return Result.success("", "取消收藏");
        }
        return Result.failed("收藏失败");
    }

    @ApiOperation("查询收藏状态")
    @PostMapping("/collection/status")
    public Result getCollectionStatus(Integer articleId, Integer userId) {
        Integer result = articleService.getCollectionStatus(articleId, userId);
        return Result.success(result);
    }

    @ApiOperation("通过用户id获取用户收藏的文章")
    @GetMapping("/{userId}/collection")
    public Result getCollectArticleByUserId(@PathVariable("userId") Integer userId) {
        List<ArticleDTO> list = articleService.getCollectArticleByUserId(userId);
        return Result.success(list);
    }


}
