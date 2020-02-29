package com.zs.campusblog.controller;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.ArticleDTO;
import com.zs.campusblog.dto.ArticleQueryParam;
import com.zs.campusblog.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//    @Autowired
//    CategoryService categoryService;
//    @Autowired
//    TagService tagService;

    @ApiOperation(value = "查询文章")
    @GetMapping(value = "/list")
    public Result<CommonPage<ArticleDTO>> getList(ArticleQueryParam articleQueryParam,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<ArticleDTO> articleList = articleService.list(articleQueryParam, pageSize, pageNum);
        return Result.success(CommonPage.restPage(articleList));
    }


}
