package com.sustech.ooad.service;

import com.sustech.ooad.entity.QuizGradeBook;
import org.springframework.stereotype.Service;

@Service
public interface QuizGradeBookService {

    void save(QuizGradeBook quizGradeBook);
}
