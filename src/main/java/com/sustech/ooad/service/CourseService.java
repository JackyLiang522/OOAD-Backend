package com.sustech.ooad.service;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    public List<Course> listAllCourses();

    public List<Chapter> listChaptersByCourseId(Long courseId);

    public void addCourse();
}
