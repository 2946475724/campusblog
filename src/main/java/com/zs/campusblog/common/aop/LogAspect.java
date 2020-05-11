package com.zs.campusblog.common.aop;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.zs.campusblog.dto.UserDetail;
import com.zs.campusblog.mbg.model.SysLog;
import com.zs.campusblog.service.SysLogService;
import com.zs.campusblog.util.AopUtil;
import com.zs.campusblog.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author zs
 * @date 2020/4/22
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private SysLog sysLog;
    /**
     * 接口请求开始时间
     */
    private Date startTime;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    SysLogService sysLogService;

    @Pointcut("@annotation(logAop)")
    public void pointcut(LogAop logAop) {

    }

    /**
     * 前置通知
     * @param joinPoint
     * @param logAop
     */
    @Before("pointcut(logAop)")
    public void doBefore(JoinPoint joinPoint, LogAop logAop) {
        sysLog = new SysLog();
        // 设置接口开始请求时间
        startTime = new Date();

        String classType = joinPoint.getTarget().getClass().getName();
        try {
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            // 获取方法的名称
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            // 获取参数名称和值
            StringBuffer sb = AopUtil.getNameAndArgs(this.getClass(), clazzName, methodName, args);
            sysLog.setParams(sb.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取ip地址
        String ip = IpUtil.getIpAddr(request);
        sysLog.setIp(ip);
        // 设置Request的请求方式 GET POST
        sysLog.setType(request.getMethod());
        // 设置调用的方法
        sysLog.setMethod(joinPoint.getSignature().getName());
        // 设置调用的类
        sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
        sysLog.setUrl(request.getRequestURI().toString());
        sysLog.setOperation(logAop.value());
        sysLog.setCreateTime(new Date());
        sysLog.setUpdateTime(new Date());
        Map<String, String> osAndBrowser = IpUtil.getOsAndBrowserInfo(request);
        sysLog.setOs(osAndBrowser.get("OS"));
        sysLog.setBrowser(osAndBrowser.get("BROWSER"));

    }

    @AfterReturning("pointcut(logAop)")
    public void doAfterReturning(LogAop logAop){
        Date endTime = new Date();
        Long spendTime = DateUtil.between(startTime, endTime, DateUnit.MS);
        // 计算请求接口花费的时间，单位毫秒
        sysLog.setSpendTime(spendTime.intValue());
        UserDetail userDetail = null;
        userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        sysLog.setUsername(userDetail.getUsername());
        sysLogService.add(sysLog);
    }
}
