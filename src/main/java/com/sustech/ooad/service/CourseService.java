package com.sustech.ooad.service;

import com.sustech.ooad.entity.Course;
import java.util.List;

public interface CourseService {
    public List<Course> listAllCourses();

    public List<Chapter> listChapterByCourseId();
}
