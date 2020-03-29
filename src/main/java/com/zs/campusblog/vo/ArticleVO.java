package com.zs.campusblog.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author zs
 * @date 2020/3/3
 * 文章表现层
 */
@Data
public class ArticleVO {
    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotBlank(message = "文章内容不能为空")
    private String content;

    private String contentHtml;

    private String coverImage;

    private String summary;

    private Integer categoryId;

    private Integer userId;

    private String tags;

    private Integer status;

}
