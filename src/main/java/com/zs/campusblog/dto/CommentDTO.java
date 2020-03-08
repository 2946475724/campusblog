package com.zs.campusblog.dto;

import com.zs.campusblog.mbg.model.Article;
import com.zs.campusblog.mbg.model.Comment;
import com.zs.campusblog.mbg.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/7
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentDTO extends Comment {
    /**
     * 本条评论是哪个用户说的
     */
    private User user;

    /**
     * 发表评论的用户名
     */
    private String userName;

    /**
     * 被回复的用户名
     */
    private String toUserName;

    /**
     * 本条评论对哪个用户说的，如果没有则为一级评论
     */
    private User toUser;

    /**
     * 本条评论下的回复
     */
    private List<CommentDTO> replyList;

    /**
     * 评论来源类型名称
     */
    private String typeName;

    /**
     * 该评论来源的文章
     */
    private Article article;
}
