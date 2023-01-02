package com.sustech.ooad.controller;

import com.sustech.ooad.entity.*;
import com.sustech.ooad.service.ChapterService;
import com.sustech.ooad.service.QuizProblemService;
import com.sustech.ooad.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private QuizProblemService quizProblemService;

    @GetMapping("/list")
    @Transactional
    public Quiz getQuizByChapterId(@RequestParam Long chapterId){
        Chapter chapter = chapterService.getChapterById(chapterId);
        return quizService.getQuizByChapter(chapter);
    }

    @PostMapping("/add")
    @Transactional
    public Quiz add(@RequestParam Long chapterId, @RequestBody List<FrontQuiz> questionList){
        Chapter chapter = chapterService.getChapterById(chapterId);
        Quiz quiz = new Quiz(chapter);
        chapter.setQuiz(quiz);
        quizService.save(quiz);
        for (FrontQuiz frontQuiz : questionList){
            QuizProblem problem = new QuizProblem(quiz, frontQuiz.getDescription(), frontQuiz.getType());

            StringBuilder answer = new StringBuilder();
            for (String ans: frontQuiz.getAnswers()){
                answer.append(ans).append("|");
            }
            problem.setAnswer(answer.toString());

            StringBuilder options = new StringBuilder();
            for (FrontData frontData : frontQuiz.getOptions()){
                options.append(frontData.getValue()).append("|");
            }
            problem.setOptions(options.toString());

            quiz.getQuizProblems().add(problem);
            problem.setQuiz(quiz);
            quizProblemService.save(problem);
        }


        return quiz;
    }
}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class FrontQuiz{
    private String id;
    private String description;
    private String type;
    private List<String> answers;
    private List<FrontData> options;
}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class FrontData{
    private long key;
    private String value;
}
