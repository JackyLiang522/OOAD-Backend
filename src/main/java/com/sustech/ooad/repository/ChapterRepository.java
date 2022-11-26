package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByChapterNumber(Long chapterNumber);

    @Override
    <S extends Chapter> S save(S s);
}
