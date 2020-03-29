package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Link;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/18
 */
public interface LinkService {

    /**
     * 获取友情链接
     */
    List<Link> list();

}
