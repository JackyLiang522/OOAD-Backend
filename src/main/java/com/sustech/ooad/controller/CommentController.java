package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class CommentController {
    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public List<Comment> list() {
        return commentService.listAllComments();
    }

    @PostMapping("")
    public void add(Chapter chapter, String contents, int id, String nickname, int user_id){
        commentService.addComment(chapter, contents, id, nickname, user_id);
    }

    @PostMapping("")
    public void delete(int user_id, int id){
        commentService.deleteComment(user_id, id);
    }

    @PostMapping("")
    public void update(int user_id, long id, String contents){
        commentService.updateComment(user_id, id, contents);
    }
}
