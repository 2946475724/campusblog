package com.zs.campusblog.service;

import com.zs.campusblog.dto.OssCallbackResult;
import com.zs.campusblog.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zs
 * @date 2020/3/24
 * oss上传管理Service
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
