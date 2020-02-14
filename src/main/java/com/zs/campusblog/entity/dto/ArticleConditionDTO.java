package com.zs.campusblog.entity.dto;

import lombok.Data;

/**
 * @author zs
 * @date 2020/2/11
 * 文章查询条件
 */
@Data
public class ArticleConditionDTO {
    private String title;
    private String content;
    private Integer status;
}
