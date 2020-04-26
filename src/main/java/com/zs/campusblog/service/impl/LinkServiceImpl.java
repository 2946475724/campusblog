package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.dao.LinkDAO;
import com.zs.campusblog.mbg.mapper.LinkMapper;
import com.zs.campusblog.mbg.model.Link;
import com.zs.campusblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zs
 * @date 2020/3/18
 * 友情链接的Service
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkMapper linkMapper;
    @Autowired
    LinkDAO linkDAO;

    @Override
    public List<Link> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return linkDAO.getLinkList(keyword);
    }

    @Override
    public int add(Link link) {
        link.setCreateTime(new Date());
        return linkMapper.insertSelective(link);
    }

    @Override
    public int deleteLinkById(Integer id) {
        return linkDAO.deleteLinkById(id);
    }

    @Override
    public int update(Link link) {
        return linkMapper.updateByPrimaryKeySelective(link);
    }

    @Override
    public Link getLinkById(Integer id) {
        return linkMapper.selectByPrimaryKey(id);
    }
}
