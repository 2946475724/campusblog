package com.zs.campusblog.service.impl;

import com.zs.campusblog.mbg.mapper.LinkMapper;
import com.zs.campusblog.mbg.model.Link;
import com.zs.campusblog.mbg.model.LinkExample;
import com.zs.campusblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Link> list() {
        LinkExample linkExample = new LinkExample();
        return linkMapper.selectByExample(linkExample);
    }
}
