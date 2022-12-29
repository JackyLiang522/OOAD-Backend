package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.service.AssignmentService;
import com.sustech.ooad.service.ChapterService;
import com.sustech.ooad.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;


    @Autowired
    private ChapterService chapterService;


    // http://localhost:8081/api/assignment/list?chapterId=
    @GetMapping("/list")
    @Transactional
    public Assignment getAssignmentsByChapterId(@RequestParam Long chapterId){
        Chapter chapter = chapterService.findChapterById(chapterId);
        return chapter.getAssignment();
    }

    // http://localhost:8081/api/assignment/add?chapterId=&&description=
    @PostMapping("/add")
    @Transactional
    public void addAssignment(@RequestParam Long chapterId, @RequestParam String description){
        Chapter chapter = chapterService.findChapterById(chapterId);
        Assignment assignment = new Assignment(description);
        chapter.setAssignment(assignment);
        assignment.setChapter(chapter);
        assignmentService.saveAssignment(assignment);
    }

}
