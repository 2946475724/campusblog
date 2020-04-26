package com.zs.campusblog.dao;

import com.zs.campusblog.dto.TagDTO;
import com.zs.campusblog.mbg.model.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/29
 */
public interface TagDAO {
    Tag selectByTagName(String tagName);

    int batchInsertTag(List<Tag> tags);

    /**
     * 根据标签id获取信息
     */
    TagDTO getTagById(Integer id);

    /**
     * 获取标签列表
     */
    List<Tag> getTagList(@Param("keyword") String keyword);

    /**
     * 获取标签数
     */
    @Select("select count(id) as tagCount from tag")
    int getTagCount();
}
