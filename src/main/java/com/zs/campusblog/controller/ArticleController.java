package com.zs.campusblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.campusblog.entity.Article;
import com.zs.campusblog.entity.PageBaseInfo;
import com.zs.campusblog.entity.ResponseBean;
import com.zs.campusblog.service.ArticleService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "根据文章id获取文章信息")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @ApiOperation(value = "获取所有文章")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseBean getAllArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleService.selectAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        int totalPages = pageInfo.getPages();
        int pageNum1 = pageInfo.getPageNum();
        int total = (int) pageInfo.getTotal();
        return ResponseBean.ok("获取成功", articles, new PageBaseInfo(pageNum1, totalPages, total));
    }

    @ApiOperation(value = "根据文章id删除文章")
    @DeleteMapping(value = "/{id}")
    public ResponseBean deleteById(@PathVariable Integer id) {
        int result = articleService.delete(id);
        if(result > 0) {
            return ResponseBean.ok("删除成功");
        }else {
            return ResponseBean.error("删除失败");
        }
    }
}
