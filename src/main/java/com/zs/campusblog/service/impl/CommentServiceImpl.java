package com.zs.campusblog.service.impl;

import com.zs.campusblog.dto.CommentDTO;
import com.zs.campusblog.mbg.mapper.CommentMapper;
import com.zs.campusblog.mbg.model.Comment;
import com.zs.campusblog.mbg.model.CommentExample;
import com.zs.campusblog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zs
 * @date 2020/3/7
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        comment.setCreateTime(currentTime);
        commentMapper.insertSelective(comment);
        return 1;
    }

    @Override
    public List<CommentDTO> getCommentList(CommentDTO commentDTO, Integer pageSize, Integer pageNum) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andArticleIdEqualTo(commentDTO.getArticleId());
        criteria.andTypeEqualTo(commentDTO.getType());
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        List<CommentDTO> newCommentList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDTO newComment = new CommentDTO();
            BeanUtils.copyProperties(comment, newComment);
            newCommentList.add(newComment);
        }
        return newCommentList;
    }
}
