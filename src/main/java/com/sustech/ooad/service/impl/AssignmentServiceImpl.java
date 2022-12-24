package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.repository.AssignmentRepository;
import com.sustech.ooad.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;


    @Override
    public void saveAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getAssignmentsByCourse(Course course) {
        return assignmentRepository.getAssignmentsByCourse(course);
    }

}
