package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Resource;

import java.util.List;

public interface ResourceService {
    /**
     * 获取所有资源列表
     */
    List<Resource> getList();

}
