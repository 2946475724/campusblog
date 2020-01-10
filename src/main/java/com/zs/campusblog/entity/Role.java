package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zs
 * @date 2019/12/29
 */
@Data
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String description;
}
