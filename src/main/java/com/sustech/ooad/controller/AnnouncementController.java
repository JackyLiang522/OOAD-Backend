package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Announcement;
import com.sustech.ooad.service.AnnouncementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/list")
    public List<Announcement> getAnnouncementByCourseId(Long courseId) {
        return announcementService.getAnnouncementByCourseId(courseId);
    }

    @PostMapping("")
    public Announcement addAnnouncement(Long courseId, String title, String content) {
        return announcementService.addAnnouncement(courseId, title, content);
    }
}
