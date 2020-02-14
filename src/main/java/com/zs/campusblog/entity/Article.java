package com.zs.campusblog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zs
 * @date 2020/1/9
 */

@Data
public class Article implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;
    private String title;
    private String coverImage;
    private String content;
    private Integer categoryId;
    private String cateName;
    private Integer userId;
    private String tags;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Boolean status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String username;
    private List<Tag> tagList;
}
