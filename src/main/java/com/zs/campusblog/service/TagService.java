package com.zs.campusblog.service;

import com.zs.campusblog.entity.Tag;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface TagService extends BaseService<Tag> {
    /**
     * 通过标签名获取标签
     * @param tagName
     * @return
     */
    Tag selectByTagName(String tagName);
}
