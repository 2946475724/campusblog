package com.zs.campusblog.entity;

/**
 * 统一返回结果给前端接口调用
 * @author zs
 * @date 2019/12/31
 */

public class Result<T> {
    /** 响应码*/
    private Integer status;
    /** 响应提示*/
    private String msg;
    /** 返回数据*/
    private T data;
    private PageBaseInfo page;

    private Result() {}

    public Result(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer status, String msg, T data, PageBaseInfo page) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PageBaseInfo getPage() {
        return page;
    }

    public void setPage(PageBaseInfo page) {
        this.page = page;
    }
}
