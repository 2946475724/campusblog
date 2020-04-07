package com.zs.campusblog.service.impl;

import com.zs.campusblog.dao.TagDAO;
import com.zs.campusblog.dto.TagDTO;
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
    @Autowired
    private TagDAO tagDAO;

    @Override
    public List<Tag> getTagList() {
        TagExample tagExample = new TagExample();
        return tagMapper.selectByExample(tagExample);
    }

    @Override
    public TagDTO getTagById(Integer id) {
        return tagDAO.getTagById(id);
    }
}
