package com.sustech.ooad.controller;


import com.sustech.ooad.entity.Client;
import com.sustech.ooad.entity.Course;
import com.sustech.ooad.service.ClientService;
import com.sustech.ooad.service.CourseService;
import java.util.List;
import java.util.stream.Collectors;
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

    // http://localhost:8081/api/course/getById?courseId=
    @GetMapping("")
    public Course getCourseById(@RequestParam Long courseId) {
        return courseService.getCourseById(courseId);
    }

    // http://localhost:8081/api/course/add?teacher=&&name=&&introduction=&&price=
    @PostMapping("/add")
    public void addCourse(@RequestParam String teacher, @RequestParam String name, @RequestParam String introduction, @RequestParam int price) {
        courseService.addCourse(teacher, name, introduction, price);
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

    // 订阅
    // http://localhost:8081/api/course/subscribe?courseId=&&clientId=
    @PostMapping("/subscribe")
    @Transactional
    public void subscribe(@RequestParam Long courseId, @RequestParam Long clientId) {
        Course course = courseService.getCourseById(courseId);
        Client client = clientService.getUserById(clientId);
        course.getClientsSubscribed().add(client);
        client.getCoursesSubscribed().add(course);
    }

    // 购买
    // http://localhost:8081/api/course/purchase?courseId=&&clientId=
    @PostMapping("/purchase")
    @Transactional
    public void purchase(@RequestParam Long courseId, @RequestParam Long clientId) {

    }
}

