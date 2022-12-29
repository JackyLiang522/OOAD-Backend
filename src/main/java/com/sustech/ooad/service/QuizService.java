package com.sustech.ooad.service;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Quiz;
import com.sustech.ooad.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
        public List<Quiz> listAllQuizzes();

    public Quiz getQuizById(Long quizId);

    public void addQuiz(Chapter chapter, String description, String options);

    public void updateQuizDescription(Long quizId, String description);

    public void updateQuizOptions(Long quizId, String options);

    List<Quiz> getQuizByChapter(Chapter chapter);
}
