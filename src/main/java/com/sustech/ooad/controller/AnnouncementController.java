package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Announcement;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.service.AnnouncementService;
import java.util.List;

import com.sustech.ooad.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private CourseService courseService;

    // http://localhost:8081/api/announcement/list?courseId=
    @GetMapping("/list")
    @Transactional
    public List<Announcement> getAnnouncementByCourseId(@RequestParam Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return announcementService.getAnnouncementByCourse(course);
    }

    // http://localhost:8081/api/announcement/add?courseId=&&title=&&content=
    @PostMapping("/add")
    @Transactional
    public void addAnnouncement(@RequestParam Long courseId, @RequestParam String title, @RequestParam String content) {
        Course course = courseService.getCourseById(courseId);
        Announcement announcement = new Announcement(title, content);
        course.getAnnouncements().add(announcement);
        announcement.setCourse(course);
        announcementService.save(announcement);

    }
}
