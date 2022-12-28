package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.service.AssignmentService;
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
    private CourseService courseService;


    // http://localhost:8081/api/assignment/list?courseId=
    @GetMapping("/list")
    @Transactional
    public List<Assignment> getAssignmentsByCourseId(@RequestParam Long courseId){
        Course course = courseService.getCourseById(courseId);
        return assignmentService.getAssignmentsByCourse(course);
    }

    // http://localhost:8081/api/assignment/add?courseId=&&description=
    @PostMapping("/add")
    @Transactional
    public void addAssignment(@RequestParam Long courseId, @RequestParam String description){
        Course course = courseService.getCourseById(courseId);
        Assignment assignment = new Assignment(description);
        course.getAssignments().add(assignment);
        assignment.setCourse(course);
        assignmentService.saveAssignment(assignment);
    }

}
