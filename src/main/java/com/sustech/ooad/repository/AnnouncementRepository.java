package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Announcement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCourseId(Long courseId);

    @Override
    <S extends Announcement> S save(S s);
}
