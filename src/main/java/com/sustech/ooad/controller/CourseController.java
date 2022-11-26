package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.entity.Result;
import com.sustech.ooad.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course/")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public List<Course> list() {
        return courseService.listAllCourses();
    }

    @GetMapping("list_chapters")
    public List<Chapter> listChaptersByCourseId (Long courseId) {
        return courseService.listChaptersByCourseId(courseId);
    }

    @PostMapping("subscribe")
    public Result subscribe(int courseId) {
        return null;
    }

    @PostMapping("")
    public void addCourse(String teacher, String name, String introduction, int price) {
        courseService.addCourse(teacher, name, introduction, price);
    }

    @PostMapping("censor")
    public void censor(Long courseId, boolean pass) {
        if (pass) {
            courseService.updateCourseStatus(courseId, 1);
        } else {
            courseService.updateCourseStatus(courseId, 2);
        }
    }
}

