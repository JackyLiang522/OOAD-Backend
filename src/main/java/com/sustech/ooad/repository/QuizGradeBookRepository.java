package com.sustech.ooad.repository;


import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Quiz;
import com.sustech.ooad.entity.QuizGradeBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizGradeBookRepository extends JpaRepository<QuizGradeBook, Long> {

    QuizGradeBook findQuizGradeBookByStudentAndQuiz(Client student, Quiz quiz);
}
