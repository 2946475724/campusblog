package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
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
    public List<Tag> getTagList(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return tagDAO.getTagList(keyword);
    }

    @Override
    public List<Tag> getTags() {
        return tagMapper.selectByExample(new TagExample());
    }

    @Override
    public List<Tag> getTagListByClickCount() {
        TagExample example = new TagExample();
        example.setOrderByClause("click_count desc");
        example.createCriteria().andStatusEqualTo(0);
        return tagMapper.selectByExample(example);
    }

    @Override
    public TagDTO getTagById(Integer id) {
        return tagDAO.getTagById(id);
    }

    @Override
    public int getTagCount() {
        return tagDAO.getTagCount();
    }

    @Override
    public int addTag(Tag tag) {
        return tagMapper.insertSelective(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        TagExample example = new TagExample();
        example.createCriteria().andIdEqualTo(tag.getId());
        return tagMapper.updateByExampleSelective(tag, example);
    }

    @Override
    public List<TagDTO> getTagListWithHot() {
        return tagDAO.getTagListWithHot();
    }

    @Override
    public List<TagDTO> getTagListWithLatest() {
        return tagDAO.getTagListWithLatest();
    }

    @Override
    public int increaseClickCount(Integer id) {
        return tagDAO.increaseClickCount(id);
    }

}
