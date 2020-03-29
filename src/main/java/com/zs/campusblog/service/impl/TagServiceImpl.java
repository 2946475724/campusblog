package com.zs.campusblog.service.impl;

import com.zs.campusblog.mbg.mapper.TagMapper;
import com.zs.campusblog.mbg.model.Tag;
import com.zs.campusblog.mbg.model.TagExample;
import com.zs.campusblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/12
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getTagList() {
        TagExample tagExample = new TagExample();
        return tagMapper.selectByExample(tagExample);
    }
}
