package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zs
 * @date 2020/1/9
 */
@Data
public class ArticleTag implements Serializable {

    private Integer id;
    private Integer articleId;
    private Integer tagId;
}
