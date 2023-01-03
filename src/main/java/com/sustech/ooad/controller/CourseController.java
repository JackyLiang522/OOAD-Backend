package com.sustech.ooad.controller;


import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.entity.TransactionRecord;
import com.sustech.ooad.service.ClientService;
import com.sustech.ooad.service.CourseService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sustech.ooad.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionRecordService transactionRecordService;

    @GetMapping("/list")
    public List<Course> list() {
        return courseService.listAllCourses().stream()
            .filter(course -> course.getStatus() == 1)
            .collect(Collectors.toList());
    }

    @GetMapping("/list_under_review")
    public List<Course> listUnderReview() {
        return courseService.listAllCourses().stream()
            .filter(course -> course.getStatus() == 0)
            .collect(Collectors.toList());
    }

    // http://localhost:8081/api/course/list_by_teacher?&&teacherId=
    @GetMapping("/list_by_teacher")
    @Transactional
    public List<Course> listCourseByTeacher(@RequestParam Long teacherId) {
        Client teacher = clientService.getUserById(teacherId);
        return courseService.getCourseByTeacher(teacher);
    }

    // http://localhost:8081/api/course/list_by_id?courseId=
    @GetMapping("/list_by_id")
    public Course listCourseById(@RequestParam Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/get_teacher")
    public Client getTeacherByCourseId(@RequestParam Long courseId) {
        return courseService.getCourseTeacher(courseId);
    }

    // http://localhost:8081/api/course/add?teacher=&&name=&&introduction=&&price=
    @PostMapping("/add")
    @Transactional
    public void addCourse(@RequestParam long teacher, @RequestParam String name, @RequestParam String introduction, @RequestParam int price) {
        Client tea = clientService.getUserById(teacher);
        courseService.addCourse(tea, name, introduction, price);
    }


    @PostMapping("/update_status")
    public void updateCourseStatus(@RequestParam Long courseId, @RequestParam int status) {
        courseService.updateCourseStatus(courseId, status);
    }

    // http://localhost:8081/api/course/censor?courseId=&&pass=
    @PostMapping("/censor")
    public void censor(@RequestParam Long courseId, @RequestParam boolean pass) {
        if (pass) {
            courseService.updateCourseStatus(courseId, 1);
        } else {
            courseService.updateCourseStatus(courseId, 2);
        }
    }

    // 订阅 即购买
    // http://localhost:8081/api/course/subscribe?courseId=&&clientId=
    @PostMapping("/subscribe")
    @Transactional
    public boolean subscribe(@RequestParam Long courseId, @RequestParam Long clientId) {
        Course course = courseService.getCourseById(courseId);
        Client client = clientService.getUserById(clientId);
        if (client.getAccount() >= course.getPrice()){
            course.getClientsSubscribed().add(client);
            client.getCoursesSubscribed().add(course);
            client.setAccount(client.getAccount()-course.getPrice());
            TransactionRecord record = new TransactionRecord(client.getAccount(), -course.getPrice(),client,new Date(), course.getCourseName());
            client.getTransactionRecords().add(record);
            transactionRecordService.save(record);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/list_subscribed")
    @Transactional
    public List<Course> listSubscribed(@RequestParam Long clientId) {
        Client client = clientService.getUserById(clientId);
        return client.getCoursesSubscribed();
    }
}

