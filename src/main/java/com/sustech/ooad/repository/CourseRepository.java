package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Transactional(readOnly = false)
    @Modifying
    @Query("update Course set status = ?2 where id = ?1")
    void updateStatus(Long id, int status);

    List<Course> findCoursesByTeacher(Client teacher);

}
