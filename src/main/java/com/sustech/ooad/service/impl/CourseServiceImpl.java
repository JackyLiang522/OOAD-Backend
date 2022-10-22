package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.service.CourseService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> listAllCourses() {
        return null;
    }

    @Override
    public List<Chapter> listChaptersByCourseId(int courseId) {
        return null;
    }
}
