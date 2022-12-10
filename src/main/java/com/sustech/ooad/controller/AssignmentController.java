package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/list")
    public List<Assignment> getAssignmentsByCourseId(Long courseId){
        return assignmentService.getByCourseId(courseId);
    }

    @PostMapping("")
    public void addAssignment(Long courseId, String description){
        assignmentService.addAssignment(courseId, description);
    }

}
