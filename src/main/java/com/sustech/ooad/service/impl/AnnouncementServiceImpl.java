package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Announcement;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.repository.AnnouncementRepository;
import com.sustech.ooad.service.AnnouncementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;


    @Override
    public Announcement addAnnouncement(Long courseId, String title, String content) {
        Announcement announcement = new Announcement(title, content);
        announcementRepository.save(announcement);
        return announcement;
    }

    @Override
    public void save(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAnnouncementByCourse(Course course) {
        return announcementRepository.findAnnouncementsByCourse(course);
    }
}
