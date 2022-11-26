package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Chapter;
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
    public List<Chapter> listChaptersByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return null;
        } else {
            return course.getChapters();
        }
    }

    @Override
    public void addCourse(String teacher, String name, String introduction, int price) {
        Course course = new Course(teacher, name, introduction, price);
        courseRepository.save(course);
    }

    @Override
    public void updateCourseStatus(Long courseId, int status) {
        Course course = courseRepository.findById(courseId).orElse(null);
        course.setStatus(status);
        courseRepository.save(course);
    }
}
