package com.zs.campusblog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author zs
 * @date 2020/3/7
 * 评论VO
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class
CommentVO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 回复某条评论的id
     */
    private Integer toId;

    /**
     * 回复某个人的UserId
     */
    private Integer toUserId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 评论来源类型
     */
    private Integer type;
}
