package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.repository.ChapterRepository;
import com.sustech.ooad.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Chapter findChapterById(Long chapterId) {
        return chapterRepository.findChapterById(chapterId);
    }

    @Override
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public List<Chapter> findChaptersBYCourse(Course course) {
        return chapterRepository.findChaptersByCourse(course);
    }

    @Override
    public Chapter getChapterById(Long chapterId) {
        return chapterRepository.getOne(chapterId);
    }

}
