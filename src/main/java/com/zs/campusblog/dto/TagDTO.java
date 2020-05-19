package com.zs.campusblog.dto;

import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zs
 * @date 2020/4/4
 */
@Data
public class TagDTO extends Tag {

    @ApiModelProperty("文章信息")
    private List<Article> articles;

    @ApiModelProperty("文章数")
    private Integer articleCount;

}
