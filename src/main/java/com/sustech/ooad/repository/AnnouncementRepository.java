package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Announcement;
import java.util.List;

import com.sustech.ooad.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {



    List<Announcement> findAnnouncementsByCourseOrderByTimeDesc(Course course);

}
