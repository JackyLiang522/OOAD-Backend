package com.sustech.ooad.service;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Comment;
import com.sustech.ooad.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChapterService {

    Chapter findChapterById(Long chapterId);

    Chapter save(Chapter chapter);

    List<Chapter> findChaptersByCourse(Course course);

    Chapter getChapterById(Long chapterId);
}
