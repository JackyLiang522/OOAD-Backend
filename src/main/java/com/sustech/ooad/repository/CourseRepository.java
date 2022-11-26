package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
