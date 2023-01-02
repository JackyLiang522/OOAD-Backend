package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Quiz;
import com.sustech.ooad.entity.QuizProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizProblemRepository extends JpaRepository<QuizProblem, Long> {

}
