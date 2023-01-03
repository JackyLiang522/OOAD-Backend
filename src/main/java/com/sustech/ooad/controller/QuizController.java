package com.sustech.ooad.controller;

import com.sustech.ooad.entity.*;
import com.sustech.ooad.service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private QuizGradeBookService quizGradeBookService;

    @Autowired
    private ClientService clientService;

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
                answer.append(ans.replace("选项 ","")).append("|");
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

    @GetMapping("/listByChapter")
    public List<FrontQuizProblem> listByChapter(@RequestParam Long chapterId){
        Chapter chapter = chapterService.getChapterById(chapterId);
        Quiz quiz = quizService.getQuizByChapter(chapter);
        List<FrontQuizProblem> problems = new ArrayList<>();
        for (QuizProblem quizProblem : quiz.getQuizProblems()){
            FrontQuizProblem frontQuizProblem = new FrontQuizProblem();
            frontQuizProblem.setDescription(quizProblem.getDescription());
            frontQuizProblem.setType(quizProblem.getType());
            frontQuizProblem.setAnswers(Arrays.asList(quizProblem.getAnswer().split("\\|")));
            frontQuizProblem.setOptions(Arrays.asList(quizProblem.getOptions().split("\\|")));
            problems.add(frontQuizProblem);
        }
        return problems;
    }

    @PostMapping("/recordGrade")
    @Transactional
    public void recordGrade(@RequestParam Long chapterId, @RequestParam Long studentId, @RequestParam double grade){
        Chapter chapter = chapterService.getChapterById(chapterId);
        Quiz quiz = chapter.getQuiz();
        Client student = clientService.getUserById(studentId);
        QuizGradeBook quizGradeBook = new QuizGradeBook((int)grade, student, quiz);
        student.getQuizGradeBooks().add(quizGradeBook);
        quiz.getQuizGradeBooks().add(quizGradeBook);
        quizGradeBookService.save(quizGradeBook);
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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class FrontQuizProblem{
    private String description;
    private String type;
    private List<String> answers;
    private List<String> options;
}