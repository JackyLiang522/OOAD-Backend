package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.QuizProblem;
import com.sustech.ooad.repository.QuizProblemRepository;
import com.sustech.ooad.repository.QuizRepository;
import com.sustech.ooad.service.QuizProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizProblemServiceImpl implements QuizProblemService {

    @Autowired
    private QuizProblemRepository quizProblemRepository;

    @Override
    public void save(QuizProblem problem) {
        quizProblemRepository.save(problem);
    }
}
