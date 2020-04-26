package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.Link;
import com.zs.campusblog.service.LinkService;
import com.zs.campusblog.vo.LinkVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result getLinkList(@RequestParam(defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Link> linkList = linkService.list(keyword, pageNum, pageSize);
        CommonPage commonPage = new CommonPage<Link>();
        return Result.success(CommonPage.restPage(linkList));
    }

    @ApiOperation(value = "添加友情链接")
    @PostMapping("/add")
    public Result addLink(Link link) {
        int result = linkService.add(link);
        if (result > 0) {
            return Result.success("", "添加成功");
        }
        return Result.success("", "更新失败");
    }

    @ApiOperation(value = "根据id获取友情链接")
    public Result getLinkById(Integer id) {
        Link link = linkService.getLinkById(id);
        return Result.success(link);
    }

    @ApiOperation(value = "更新友情链接")
    @PostMapping("/update")
    public Result updateLink(LinkVO linkVO) {
        Link link = new Link();
        BeanUtils.copyProperties(linkVO, link);
        int result = linkService.update(link);
        if (result > 0) {
            return Result.success("", "更新成功");
        }
        return Result.success("", "更新失败");
    }

    @ApiOperation(value = "根据id删除友情链接")
    @PostMapping("/delete")
    public Result deleteLinkById(@RequestParam("id") Integer id) {
        linkService.deleteLinkById(id);
        return Result.success("");
    }

}
