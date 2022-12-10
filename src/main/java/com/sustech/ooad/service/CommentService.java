package com.sustech.ooad.service;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public Comment addComment(Chapter chapter, String contents, int id, String nickname, int user_id);

    public boolean deleteComment(int user_id, long id);

    public Comment updateComment(int user_id, long id, String contents);

    public List<Comment> listAllComments();

}
