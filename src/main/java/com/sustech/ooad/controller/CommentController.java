package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.service.ChapterService;
import com.sustech.ooad.service.ClientService;
import com.sustech.ooad.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public List<FrontComment> list(@RequestParam Long chapterId) {
        Chapter chapter = chapterService.findChapterById(chapterId);
        List<Comment> comments = commentService.findCommentsByChapter(chapter);
        List<FrontComment> frontComments = new ArrayList<>();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式
        for (Comment c : comments){
            FrontComment frontComment = new FrontComment();
            frontComment.setContents(c.getContents());
            frontComment.setNickname(c.getNickname());
            frontComment.setCreateTime(sformat.format(c.getCreateTime()));
            frontComments.add(frontComment);
        }
        return frontComments;
    }



    // http://localhost:8081/api/comment/add?chapterId=&&contents=&&nickname=&&userId=
    @PostMapping("/add")
    @Transactional
    public void add(@RequestParam Long chapterId, @RequestParam String contents, @RequestParam String nickname, @RequestParam long userId){
        Chapter chapter = chapterService.findChapterById(chapterId);
        Client user = clientService.getUserById(userId);
        Comment comment = new Comment(contents, nickname, new Date());
        chapter.getComments().add(comment);
        user.getComments().add(comment);
        comment.setChapter(chapter);
        comment.setUser(user);
        commentService.save(comment);
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class FrontComment{
    private String nickname;
    private String contents;
    private String createTime;
}

