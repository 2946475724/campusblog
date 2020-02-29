package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.Tag;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface TagService {
    /**
     * 分页查询文章
     */
    List<Tag> list(Tag tag, Integer pageSize, Integer pageNum);
}
