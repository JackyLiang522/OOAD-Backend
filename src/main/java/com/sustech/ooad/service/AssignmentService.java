package com.sustech.ooad.service;

import com.sustech.ooad.entity.Assignment;
import com.sustech.ooad.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentService {

    void saveAssignment(Assignment assignment);

}

