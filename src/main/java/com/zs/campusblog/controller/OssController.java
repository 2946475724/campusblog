package com.zs.campusblog.controller;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.OssCallbackResult;
import com.zs.campusblog.dto.OssPolicyResult;
import com.zs.campusblog.service.impl.OssServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zs
 * @date 2020/3/24
 * Oss相关操作接口
 */
@RestController
@RequestMapping(value = "/aliyun/oss")
public class OssController {
        @Autowired
        private OssServiceImpl ossService;

        @ApiOperation(value = "oss上传签名生成")
        @GetMapping(value = "/policy")
        @ResponseBody
        public Result<OssPolicyResult> policy() {
            OssPolicyResult result = ossService.policy();
            return Result.success(result);
        }

        @ApiOperation(value = "oss上传成功回调")
        @PostMapping(value = "/callback")
        @ResponseBody
        public Result<OssCallbackResult> callback(HttpServletRequest request) {
            OssCallbackResult ossCallbackResult = ossService.callback(request);
            return Result.success(ossCallbackResult);
        }
}
