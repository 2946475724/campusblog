package com.zs.campusblog.service;

import com.zs.campusblog.mbg.model.SysLog;

import java.util.List;

public interface SysLogService {
    /**
     * 保存系统日志
     */
    int add(SysLog sysLog);

    /**
     * 获取日志列表
     */
    List<SysLog> getSysLogList(String username, Integer pageNum, Integer pageSize);
}
