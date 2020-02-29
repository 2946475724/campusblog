package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Tag;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/29
 */
public interface TagDAO {
    Tag selectByTagName(String tagName);

    int batchInsertTag(List<Tag> tags);
}
