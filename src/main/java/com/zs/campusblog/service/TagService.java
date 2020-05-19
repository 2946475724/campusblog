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
     * 通过点击量获取标签列表
     */
    List<Tag> getTagListByClickCount();

    /**
     * 根据标签id获取标签信息
     */
    TagDTO getTagById(Integer tagId);

    /**
     * 获取标签数
     */
    int getTagCount();

    /**
     * 添加标签
     */
    int addTag(Tag tag);

    /**
     * 编辑标签
     */
    int updateTag(Tag tag);

    /**
     * 根据标签对应的文章数获取标签列表
     */
    List<TagDTO> getTagListWithHot();

    /**
     * 根据创建时间获取标签列表
     */
    List<TagDTO> getTagListWithLatest();

    /**
     * 添加标签点击量
     */
    int increaseClickCount(Integer id);

}
