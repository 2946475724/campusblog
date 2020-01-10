package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zs
 * @date 2019/12/31
 */

@Data
public class MenuMeta implements Serializable {
    private Boolean keepAlive;
    private Boolean requireAuth;

}
