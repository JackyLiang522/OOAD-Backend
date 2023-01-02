package com.sustech.ooad.service;

import com.sustech.ooad.entity.QuizProblem;
import org.springframework.stereotype.Service;

@Service
public interface QuizProblemService {

    void save(QuizProblem problem);
}
