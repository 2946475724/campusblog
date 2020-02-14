package com.zs.campusblog.enums;

/**
 * @author zs
 * @date 2020/2/13
 */
public enum ResultEnum {
    SUCCESS(200, "成功"),
    ERROR(-1, "失败");

    private Integer status;
    private String msg;

    ResultEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
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
}
