package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.QuizGradeBook;
import com.sustech.ooad.repository.QuizGradeBookRepository;
import com.sustech.ooad.service.QuizGradeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizGradeBookServiceImpl implements QuizGradeBookService {

    @Autowired
    private QuizGradeBookRepository quizGradeBookRepository;

    @Override
    public void save(QuizGradeBook quizGradeBook) {
        quizGradeBookRepository.save(quizGradeBook);
    }
}
