package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zs
 * @date 2020/1/9
 */
@Data
public class Category implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Timestamp createTime;
}
