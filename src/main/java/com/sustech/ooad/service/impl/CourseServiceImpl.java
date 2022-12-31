package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.repository.CourseRepository;
import com.sustech.ooad.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public void addCourse(Client teacher, String name, String introduction, int price) {
        Course course = new Course(teacher, name, introduction, price);
        teacher.getCoursesCreated().add(course);
        courseRepository.save(course);
    }

    @Override
    public void updateCourseStatus(Long courseId, int status) {
        /*Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        course.setStatus(status);
        courseRepository.save(course);*/
        courseRepository.updateStatus(courseId, status);

    }

    @Override
    public List<Course> getCourseByTeacher(Client teacher) {
        return courseRepository.findCoursesByTeacher(teacher);
    }

    @Override
    public Client getCourseTeacher(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        return course != null ? course.getTeacher() : null;
    }

}
