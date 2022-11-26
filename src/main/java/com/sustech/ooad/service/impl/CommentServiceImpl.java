package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.repository.CommentRepository;
import com.sustech.ooad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Chapter chapter, String contents, int id, String nickname, int user_id) {
        Comment comment = new Comment(chapter, contents, id, nickname, user_id);
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(int user_id, long id) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c != null && c.getUser_id() == user_id){
            commentRepository.delete(c);
            return true;
        }
        return false;
    }

    @Override
    public Comment updateComment(int user_id, long id, String contents) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c != null && c.getUser_id() == user_id){
            Comment newComment = new Comment(c.getChapter(), contents, (int)id, c.getNickname(), c.getUser_id());
            commentRepository.delete(c);
            return commentRepository.save(newComment);
        }
        return null;
    }

    @Override
    public List<Comment> listAllComments() {
        return commentRepository.findAll();
    }
}
