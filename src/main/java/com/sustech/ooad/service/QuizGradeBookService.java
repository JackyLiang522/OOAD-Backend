package com.sustech.ooad.service;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Quiz;
import com.sustech.ooad.entity.QuizGradeBook;
import org.springframework.stereotype.Service;

@Service
public interface QuizGradeBookService {

    void save(QuizGradeBook quizGradeBook);

    QuizGradeBook getByStudentAndQuiz(Client student, Quiz quiz);
}
