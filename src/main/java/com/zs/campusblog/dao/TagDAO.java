package com.zs.campusblog.dao;

import com.zs.campusblog.dto.TagDTO;
import com.zs.campusblog.mbg.model.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据标签对应的文章数获取标签列表
     */
    @Select("select t.id, t.name, t.click_count, count(t.id) as articleCount from tag t left join article_tag at on t.id = at.tag_id where t.status = 0 GROUP BY t.id ORDER BY t.click_count desc")
    List<TagDTO> getTagListWithHot();

    /**
     * 根据创建时间获取标签列表
     */
    @Select("select t.id, t.name, t.click_count, count(t.id) as articleCount from tag t left join article_tag at on t.id = at.tag_id where t.status = 0 GROUP BY t.id ORDER BY t.create_time desc")
    List<TagDTO> getTagListWithLatest();

    /**
     * 增加文章点击量
     */
    @Update("update tag set click_count = click_count + 1 where id = #{id}")
    int increaseClickCount(@Param("id") Integer id);
}
