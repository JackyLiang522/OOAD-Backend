package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.AssignmentGradeBook;
import com.sustech.ooad.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentGradeBookRepository extends JpaRepository<AssignmentGradeBook, Long> {

    AssignmentGradeBook findAssignmentGradeBookById(Long id);


    AssignmentGradeBook findAssignmentGradeBookByStudentAndAssignment(Client student, Assignment assignment);
}
