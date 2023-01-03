package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findQuizByChapter(Chapter chapter);



}
