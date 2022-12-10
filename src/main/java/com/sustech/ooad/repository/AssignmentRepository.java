package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByCourseId();

    void addAssignment(Long courseId, String description);

}
