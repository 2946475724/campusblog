package com.zs.campusblog.controller;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.service.LinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zs
 * @date 2020/3/21
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @ApiOperation(value = "获取友情链接")
    @GetMapping("/list")
    public Result list() {
        return Result.success(linkService.list());
    }


}
