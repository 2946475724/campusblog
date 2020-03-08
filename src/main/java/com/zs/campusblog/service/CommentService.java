package com.zs.campusblog.service;

import com.zs.campusblog.dto.CommentDTO;
import com.zs.campusblog.mbg.model.Comment;

import java.util.List;

/**
 * @author zs
 * @date 2020/3/7
 * 评论的Service
 */
public interface CommentService {

    /**
     * 新增评论
     */
    int add(Comment comment);

    /**
     * 获取评论列表
     */
    List<CommentDTO> getCommentList(CommentDTO commentDTO, Integer pageSize, Integer pageNum);
}
