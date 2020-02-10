package com.zs.campusblog.entity.dto;

import com.zs.campusblog.entity.Tag;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zs
 * @date 2020/2/10
 */
@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer categoryId;
    private Integer userId;
    private Boolean status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private List<Tag> tags;
}
