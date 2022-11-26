package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comment/")
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
    public Comment add(Chapter chapter, String contents, int id, String nickname, int user_id){
        return commentService.addComment(chapter, contents, id, nickname, user_id);
    }

    @PostMapping("")
    public void delete(int user_id, int id){
        commentService.deleteComment(user_id, id);
    }

    @PostMapping("")
    public Comment update(int user_id, long id, String contents){
        return commentService.updateComment(user_id, id, contents);
    }
}
