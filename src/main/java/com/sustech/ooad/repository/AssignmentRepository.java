package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("select a from Assignment a where a.courseId = ?1")
    List<Assignment> findByCourseId(Long courseId);

}
