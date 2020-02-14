package com.zs.campusblog.util;

import com.zs.campusblog.entity.PageBaseInfo;
import com.zs.campusblog.entity.Result;
import com.zs.campusblog.enums.ResultEnum;

/**
 * @author zs
 * @date 2020/2/13
 * Result类只作为一个实体类，用此工具类来实现Result的具体功能比较直观，而不是每次直接给 Result 设置值
 */
public class ResultUtil {
    private static String msg;
    private static Object object;

    public static Result success() {
        return new Result(ResultEnum.SUCCESS.getStatus(), ResultEnum.SUCCESS.getMsg(), ResultEnum.SUCCESS.getMsg());
    }

    /**
     * 使用默认给出的成功响应，只需填充数据即可
     * @param object
     * @return
     */
    public static Result success(Object object) {
        return new Result(ResultEnum.SUCCESS.getStatus(), ResultEnum.SUCCESS.getMsg(), object);
    }

    /**
     * 自定义成功响应提示
     * @param msg
     * @param object
     * @return
     */
    public static Result success(String msg, Object object) {
        return new Result(ResultEnum.SUCCESS.getStatus(), ResultEnum.SUCCESS.getMsg(), object);
    }

    /**
     * 自定义成功响应提示
     * @param msg
     * @param object
     * @param pageBaseInfo
     * @return
     */
    public static Result success(String msg, Object object, PageBaseInfo pageBaseInfo) {
        return new Result(ResultEnum.SUCCESS.getStatus(), ResultEnum.SUCCESS.getMsg(), object, pageBaseInfo);
    }

    /**
     * 使用默认的失败响应码，自定义错误提示，数据为空
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        return new Result(ResultEnum.ERROR.getStatus(), msg, null);
    }

    /**
     * 自定义的错误码和错误提示，数据为空
     * @param status
     * @param msg
     * @return
     */
    public static Result error(Integer status, String msg) {
        return new Result(status, msg, null);
    }
}
