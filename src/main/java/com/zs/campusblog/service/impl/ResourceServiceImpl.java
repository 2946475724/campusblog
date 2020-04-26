package com.zs.campusblog.service.impl;

import com.zs.campusblog.mbg.mapper.ResourceMapper;
import com.zs.campusblog.mbg.model.Resource;
import com.zs.campusblog.mbg.model.ResourceExample;
import com.zs.campusblog.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/4/24
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getList() {
        return resourceMapper.selectByExample(new ResourceExample());
    }
}
