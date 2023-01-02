package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findQuizByChapter(Chapter chapter);



}
