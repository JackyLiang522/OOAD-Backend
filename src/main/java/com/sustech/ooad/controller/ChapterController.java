package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.service.ChapterService;
import com.sustech.ooad.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CourseService courseService;

    // http://localhost:8081/api/chapter/list?courseId=
    @GetMapping("/list")
    @Transactional
    public List<Chapter> listChaptersByCourseId(@RequestParam Long courseId){
        Course course = courseService.getCourseById(courseId);
        return chapterService.findChaptersBYCourse(course);
    }

    // http://localhost:8081/api/chapter/add?courseId=&&chapterName=
    @PostMapping("/add")
    @Transactional
    public Chapter addChapter(@RequestParam Long courseId, @RequestParam String chapterName){
        Course course = courseService.getCourseById(courseId);
        Chapter chapter = new Chapter(chapterName);
        chapter.setCourse(course);
        course.getChapters().add(chapter);
        return chapterService.save(chapter);
    }

    @PutMapping("/update")
    @Transactional
    public Chapter updateChapter(@RequestParam Long chapterId, @RequestParam String chapterName) {
        Chapter chapter = chapterService.getChapterById(chapterId);
        chapter.setName(chapterName);
        return chapterService.save(chapter);
    }
}
