package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
