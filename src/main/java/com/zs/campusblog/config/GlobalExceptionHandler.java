package com.zs.campusblog.config;

import com.zs.campusblog.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zs
 * @date 2020/4/14
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 缺少请求参数异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result requestNotReadable(HttpMessageNotReadableException ex) {
        log.error("\"缺少请求参数：" +  ex.getMessage());
        return Result.failed("缺少必要的请求参数");
    }

    /**
     * 空指针异常处理
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常：" + ex.getMessage());
        return Result.failed("空指针异常了");
    }

    /**
     * 请求参数异常处理
     */
    public Result handleMethodArgumentNotValidExceprtion(MethodArgumentNotValidException ex) {
        log.error("方法参数未校验：" +  ex.getMessage());
        return Result.failed("方法参数未校验");
    }

    @ExceptionHandler({CustomException.class})
    public Result handleCustomException(CustomException ex) {
        return Result.failed(ex.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public Object AccessDeniedExceptionHandler(AccessDeniedException exception) throws Exception {
        return Result.failed("您没有访问权限！");
    }

    @ExceptionHandler({AuthenticationException.class})
    public Result handleAuthenticationException(AuthenticationException ex) {
        return Result.failed(ex.getMessage());
    }

    /**
     * 系统异常 预期以外异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleUnexpectedServer(Exception ex) {
        log.error("系统异常：" + ex);
        return Result.failed("系统发生异常，请联系管理员");
    }




}
