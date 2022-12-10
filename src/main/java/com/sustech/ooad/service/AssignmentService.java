package com.sustech.ooad.service;

import com.sustech.ooad.entity.Assignment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentService {
    List<Assignment> getByCourseId(Long courseId);

    void addAssignment(Long courseId, String description);
}
