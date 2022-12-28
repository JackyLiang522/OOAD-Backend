package com.sustech.ooad.service;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public void deleteComment(int userId, long id);

    public Comment updateComment(int userId, long id, String contents);

    public List<Comment> listAllComments();

    Comment save(Comment comment);

    List<Comment> findCommentsByChapter(Chapter chapter);
}
