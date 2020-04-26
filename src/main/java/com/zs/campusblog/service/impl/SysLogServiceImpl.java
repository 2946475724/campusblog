package com.zs.campusblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.campusblog.mbg.mapper.SysLogMapper;
import com.zs.campusblog.mbg.model.SysLog;
import com.zs.campusblog.mbg.model.SysLogExample;
import com.zs.campusblog.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zs
 * @date 2020/4/22
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public int add(SysLog sysLog) {
        return sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public List<SysLog> getSysLogList(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SysLogExample example = new SysLogExample();
        example.setOrderByClause("id desc");
        if (!username.isEmpty()) {
            example.createCriteria().andUsernameEqualTo(username);
        }
        return sysLogMapper.selectByExample(example);
    }
}
