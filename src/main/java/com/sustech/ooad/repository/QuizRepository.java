package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Chapter;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Transactional(readOnly = false)
    @Modifying
    @Query("update Quiz set description = ?2 where id = ?1")
    void updateDescription(Long id, String description);

    @Transactional(readOnly = false)
    @Modifying
    @Query("update Quiz set options = ?2 where id = ?1")
    void updateOptions(Long id, String option);

    List<Quiz> findQuizzesByChapter(Chapter chapter);

}
