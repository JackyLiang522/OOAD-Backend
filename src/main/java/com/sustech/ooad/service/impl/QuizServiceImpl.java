package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Quiz;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.repository.QuizRepository;
import com.sustech.ooad.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Override
    public List<Quiz> listAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }


    @Override
    public void addQuiz(Chapter chapter, String description, String options) {
        /*
        Quiz quiz = new Quiz(chapter, description, options);
        chapter.getQuizzes().add(quiz);
        quizRepository.save(quiz);*/
    }

    @Override
    public Quiz getQuizByChapter(Chapter chapter) {
        return quizRepository.findQuizByChapter(chapter);
    }

    @Override
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }


}
