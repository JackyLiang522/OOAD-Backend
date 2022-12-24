package com.sustech.ooad.service;

import com.sustech.ooad.entity.Announcement;
import java.util.List;

import com.sustech.ooad.entity.Course;
import org.springframework.stereotype.Service;

@Service
public interface AnnouncementService {
    

    Announcement addAnnouncement(Long courseId, String title, String content);

    void save(Announcement announcement);

    List<Announcement> getAnnouncementByCourse(Course course);
}
