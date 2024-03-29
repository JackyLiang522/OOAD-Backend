package com.sustech.ooad.service;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    public List<Course> listAllCourses();

    public Course getCourseById(Long courseId);

    public Course addCourse(Client tea, String teacher, String introduction, int price);

    public void updateCourseStatus(Long courseId, int status);


    List<Course> getCourseByTeacher(Client teacher);

    Client getCourseTeacher(Long courseId);
}
