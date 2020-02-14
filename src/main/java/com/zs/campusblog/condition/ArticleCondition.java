package com.zs.campusblog.condition;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author zs
 * @date 2020/2/14
 */
@Data
public class ArticleCondition extends BaseCondition{
    private Integer id;
    private String title;
    private String content;
    private Timestamp createTime;
}
