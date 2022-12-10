package com.sustech.ooad.service;

import com.sustech.ooad.entity.Announcement;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AnnouncementService {

    public List<Announcement> getAnnouncementByCourseId(Long courseId);

    Announcement addAnnouncement(Long courseId, String title, String content);
}
