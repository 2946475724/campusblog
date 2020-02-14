package com.zs.campusblog.mapper;

import com.zs.campusblog.entity.Tag;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
public interface TagMapper extends Mapper<Tag> {

    Tag selectByTagName(String tagName);

    int batchInsertTag(List<Tag> tags);
}
