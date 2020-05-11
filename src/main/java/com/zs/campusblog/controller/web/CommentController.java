package com.zs.campusblog.controller.web;

import com.zs.campusblog.common.Result;
import com.zs.campusblog.dto.CommentDTO;
import com.zs.campusblog.mbg.mapper.UserMapper;
import com.zs.campusblog.mbg.model.Comment;
import com.zs.campusblog.mbg.model.User;
import com.zs.campusblog.mbg.model.UserExample;
import com.zs.campusblog.service.ArticleService;
import com.zs.campusblog.service.CommentService;
import com.zs.campusblog.service.UserService;
import com.zs.campusblog.vo.CommentVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zs
 * @date 2020/3/7
 * 评论接口
 */
@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "增加评论")
    @PostMapping("/add")
    public Result add(@RequestBody CommentVO commentVO, BindingResult result) {
        Comment comment = new Comment();
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, comment);
        BeanUtils.copyProperties(commentVO, commentDTO);
        commentService.add(comment);
        articleService.addComment(commentVO.getArticleId());
        User user = userService.getUserById(commentVO.getUserId());
        commentDTO.setUser(user);
        return Result.success(commentDTO);
    }

    @ApiOperation(value = "获取评论列表")
    @GetMapping("/list")
    public Result getCommentList(CommentVO commentVO,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, commentDTO);
        List<CommentDTO> commentList = commentService.getCommentList(commentDTO, pageSize, pageNum);

        List<Integer> userIdList = new ArrayList<>();
        commentList.forEach(item -> {
            Integer userId = item.getUserId();
            Integer toUserId = item.getToUserId();
            if (userId != null) {
                userIdList.add(item.getUserId());
            }
            if (toUserId != null) {
                userIdList.add(item.getToUserId());
            }
        });

        List<User> userList = new ArrayList<>();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!userIdList.isEmpty()) {
            criteria.andIdIn(userIdList);
        }
        userList = userMapper.selectByExample(userExample);

        Map<Integer, User> userMap = new HashMap<>();
        userList.forEach(item -> {
            userMap.put(item.getId(), item);
        });

        Map<Integer, CommentDTO> commentMap = new HashMap<>();
        commentList.forEach(item -> {
            if (item.getUserId() != null) {
                item.setUser(userMap.get(item.getUserId()));
            }
            if (item.getToUserId() != null) {
                item.setToUser(userMap.get(item.getToUserId()));
            }
            commentMap.put(item.getId(), item);
        });

        Map<Integer, List<CommentDTO>> toCommentListMap = new HashMap<>();
        for (int i = 0; i < commentList.size(); i++) {
            List<CommentDTO> tempList = new ArrayList<>();
            for (int j = 0; j < commentList.size(); j++) {
                if (commentList.get(i).getId().equals(commentList.get(j).getToId())) {
                    tempList.add(commentList.get(j));
                }
            }
            toCommentListMap.put(commentList.get(i).getId(), tempList);
        }

        List<CommentDTO> firstComment = new ArrayList<>();
        commentList.forEach(item -> {
            if (item.getToId() == null) {
                firstComment.add(item);
            }
        });

        return Result.success(getCommentReplys(firstComment, toCommentListMap));
    }

    /**
     * 获取评论所有回复
     */
    private List<CommentDTO> getCommentReplys(List<CommentDTO> commentList, Map<Integer, List<CommentDTO>> toCommentListMap) {
        if (commentList == null || commentList.size() == 0) {
            return new ArrayList<>();
        } else {
            commentList.forEach(item -> {
                Integer commentId = item.getId();
                List<CommentDTO> replyCommentList = toCommentListMap.get(commentId);
                List<CommentDTO> replyComments = getCommentReplys(replyCommentList, toCommentListMap);
                item.setReplyList(getCommentReplys(replyCommentList, toCommentListMap));
            });
            return commentList;
        }
    }
}
