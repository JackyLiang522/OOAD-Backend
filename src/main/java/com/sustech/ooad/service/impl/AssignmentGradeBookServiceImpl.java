package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.AssignmentGradeBook;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.repository.AssignmentGradeBookRepository;
import com.sustech.ooad.service.AssignmentGradeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentGradeBookServiceImpl implements AssignmentGradeBookService {

    @Autowired
    private AssignmentGradeBookRepository assignmentGradeBookRepository;

    @Override
    public void save(AssignmentGradeBook assignmentGradeBook) {
        assignmentGradeBookRepository.save(assignmentGradeBook);
    }

    @Override
    public AssignmentGradeBook getById(Long assignmentGradeBookId) {
        return assignmentGradeBookRepository.findAssignmentGradeBookById(assignmentGradeBookId);
    }

    @Override
    public AssignmentGradeBook getByStudentAndAssignment(Client student, Assignment assignment) {
        return assignmentGradeBookRepository.findAssignmentGradeBookByStudentAndAssignment(student, assignment);
    }
}
