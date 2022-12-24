package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.repository.ClientRepository;
import com.sustech.ooad.repository.CommentRepository;
import com.sustech.ooad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public void deleteComment(int userId, long id) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c != null && c.getUser().getId() == userId){
            commentRepository.delete(c);
        }
    }

    @Override
    public Comment updateComment(int userId, long id, String contents) {
        Comment c = commentRepository.findById(id).orElse(null);
        if (c != null && c.getUser().getId() == userId){
            Chapter chapter = c.getChapter();
            Client user = c.getUser();
            Comment newComment = new Comment(contents, c.getNickname(),  new Date());
            chapter.getComments().remove(c);
            chapter.getComments().add(newComment);
            user.getComments().remove(c);
            user.getComments().add(c);
            newComment.setChapter(chapter);
            newComment.setUser(user);
            c.setChapter(null);
            c.setUser(null);
            commentRepository.delete(c);
            return commentRepository.save(newComment);
        }
        return null;
    }

    @Override
    public List<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByChapter(Chapter chapter) {
        return commentRepository.findCommentsByChapter(chapter);
    }
}
