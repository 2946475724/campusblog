package com.zs.campusblog.dto;

import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/29
 * 创建和修改文章时的参数
 */
@Data
public class ArticleDTO extends Article {
    @ApiModelProperty("作者")
    private String username;
    private String cateName;
    private List<Tag> tagList;
}
