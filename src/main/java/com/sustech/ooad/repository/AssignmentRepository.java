package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Assignment findAssignmentById(Long assignmentId);
}
