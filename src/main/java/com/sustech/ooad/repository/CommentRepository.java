package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findCommentByChapterOrderByCreateTimeDesc(Chapter chapter);

}
