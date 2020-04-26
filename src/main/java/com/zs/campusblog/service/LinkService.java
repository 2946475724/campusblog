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
    List<Link> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加友情链接
     */
    int add(Link link);

    /**
     * 删除友情链接
     */
    int deleteLinkById(Integer id);

    /**
     * 更新友情链接
     */
    int update(Link link);

    /**
     * 根据id获取友情链接
     */
    Link getLinkById(Integer id);

}
