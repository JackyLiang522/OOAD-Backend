package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.service.ChapterService;
import com.sustech.ooad.service.ClientService;
import com.sustech.ooad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ChapterService chapterService;


    @Autowired
    private ClientService clientService;


    // http://localhost:8081/api/comment/list?chapterId=
    @GetMapping("/list")
    public List<Comment> list(@RequestParam Long chapterId) {
        Chapter chapter = chapterService.findChapterById(chapterId);
        return commentService.findCommentsByChapter(chapter);
    }

    // http://localhost:8081/api/comment/add?chapterId=&&contents=&&nickname=&&userId=
    @PostMapping("/add")
    @Transactional
    public Comment add(@RequestParam Long chapterId, @RequestParam String contents, @RequestParam String nickname, @RequestParam long userId){
        Chapter chapter = chapterService.findChapterById(chapterId);
        Client user = clientService.getUserById(userId);
        Comment comment = new Comment(contents, nickname, new Date());
        chapter.getComments().add(comment);
        user.getComments().add(comment);
        comment.setChapter(chapter);
        comment.setUser(user);
        return commentService.save(comment);
    }

    // http://localhost:8081/api/comment/delete?userId=&&commentId=
    @DeleteMapping("/delete")
    @Transactional
    public void delete(@RequestParam int userId, @RequestParam long commentId){
        commentService.deleteComment(userId, commentId);
    }

    // http://localhost:8081/api/comment/update?userId=&&commentId=&&contents=
    @PostMapping("/update")
    @Transactional
    public Comment update(@RequestParam int userId, @RequestParam long commentId, @RequestParam String contents){
        return commentService.updateComment(userId, commentId, contents);
    }

}


