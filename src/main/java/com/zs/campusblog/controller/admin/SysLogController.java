package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.mbg.model.SysLog;
import com.zs.campusblog.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zs
 * @date 2020/4/23
 */
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Autowired
    SysLogService logService;

    @ApiOperation("获取日志列表")
    @GetMapping("/list")
    public Result getLogList(@RequestParam(name = "username", required = false) String username,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<SysLog> sysLogList = logService.getSysLogList(username, pageNum, pageSize);
        return Result.success(CommonPage.restPage(sysLogList));
    }
}
