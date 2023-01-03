package com.sustech.ooad.service;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.AssignmentGradeBook;
import com.sustech.ooad.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface AssignmentGradeBookService {
    void save(AssignmentGradeBook assignmentGradeBook);

    AssignmentGradeBook getById(Long assignmentGradeBookId);

    AssignmentGradeBook getByStudentAndAssignment(Client student, Assignment assignment);
}
