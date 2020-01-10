package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */

@Data
public class Article implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Integer categoryId;
    private Integer userId;
    private Boolean status;
    private Timestamp createTime;
    private Timestamp updateTime;
//    private String username;
    private List<Tag> tags;
}
