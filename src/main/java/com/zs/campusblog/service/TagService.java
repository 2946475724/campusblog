package com.zs.campusblog.service;

import com.zs.campusblog.dto.TagDTO;
import com.zs.campusblog.mbg.model.Tag;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface TagService {
    /**
     * 获取所有标签
     */
    List<Tag> getTagList(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 前台获取所有标签
     */
    List<Tag> getTags();

    /**
     * 根据标签id获取标签信息
     */
    TagDTO getTagById(Integer tagId);

    /**
     * 获取标签数
     */
    int getTagCount();
}
