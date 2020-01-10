package com.zs.campusblog.entity;

/**
 * @author zs
 * @date 2019/12/31
 */

public class ResponseBean {
    private Integer status;
    private String msg;
    private Object data;
    private PageBaseInfo page;

    private ResponseBean() {

    }

    private ResponseBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ResponseBean(Integer status, String msg, Object data, PageBaseInfo page) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    public static ResponseBean ok(String msg, Object data) {
        return new ResponseBean(200, msg, data);
    }

    public static ResponseBean ok(String msg, Object data, PageBaseInfo page) {
        return new ResponseBean(200, msg, data, page);
    }

    public static ResponseBean ok(String msg) {
        return new ResponseBean(200, msg, null);
    }

    public static ResponseBean error(String msg, Object data) {
        return new ResponseBean(500, msg, data);
    }

    public static ResponseBean error(String msg) {
        return new ResponseBean(500, msg, null);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageBaseInfo getPage() {
        return page;
    }

    public void setPage(PageBaseInfo page) {
        this.page = page;
    }
}
