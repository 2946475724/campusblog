package com.zs.campusblog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zs
 * @date 2020/2/29
 * 文章查询参数
 */
@Data
public class ArticleQueryParam {
    @ApiModelProperty("文章名称模糊关键字")
    private String keyword;

    private String title;

    private String categoryName;

}
