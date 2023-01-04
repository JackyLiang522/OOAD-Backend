package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("select c from Chapter c where c.id = ?1")
    Chapter findChapterById(Long chapterId);


    List<Chapter> findChaptersByCourseOrderById(Course course);
}
