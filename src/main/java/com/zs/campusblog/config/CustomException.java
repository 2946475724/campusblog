package com.zs.campusblog.config;

import lombok.Data;

/**
 * @author zs
 * @date 2020/5/8
 */
@Data
public class CustomException extends Exception{
    private int code;
    private String message;

    public CustomException() {}

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
