package com.sustech.ooad.controller;


import com.sustech.ooad.entity.*;
import com.sustech.ooad.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
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

    @Autowired
    private AssignmentGradeBookService assignmentGradeBookService;

    @Autowired
    private QuizGradeBookService quizGradeBookService;

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
        return courseService.getCourseByTeacher(teacher).stream()
            .filter(course -> course.getStatus() == 1)
            .collect(Collectors.toList());
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
    public long addCourse(@RequestParam long teacher, @RequestParam String name, @RequestParam String introduction, @RequestParam int price) {
        Client tea = clientService.getUserById(teacher);
        Course c = courseService.addCourse(tea, name, introduction, price);
        return c.getId();
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
        return client.getCoursesSubscribed().stream()
            .filter(course -> course.getStatus() == 1)
            .collect(Collectors.toList());
    }


    @GetMapping("/listHwData")
    @Transactional
    public List<HwData> listHwData(@RequestParam Long courseId, @RequestParam Long studentId){
        List<HwData> hwDataList = new ArrayList<>();
        List<Chapter> chapters = courseService.getCourseById(courseId).getChapters();
        Client student = clientService.getUserById(studentId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Chapter chapter : chapters){
            Assignment assignment = chapter.getAssignment();
            if (assignment != null){
                HwData hwData = new HwData();
                hwData.setTitle(chapter.getName());
                hwData.setChapterId(String.valueOf(chapter.getId()));
                hwData.setDate(sdf.format(assignment.getDeadline()));
                AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getByStudentAndAssignment(student, assignment);
                if (assignmentGradeBook == null){
                    hwData.setState("未完成");
                    hwData.setScore("-");
                } else if (!assignmentGradeBook.isRead()){
                    hwData.setState("未批改");
                    hwData.setScore("-");
                } else {
                    hwData.setState("已完成");
                    hwData.setScore(String.valueOf(assignmentGradeBook.getGrade()));
                }
                hwDataList.add(hwData);
            }
        }
        return hwDataList;
    }


    @GetMapping("/listPgData")
    @Transactional
    public List<HwData> listPgData(@RequestParam Long courseId, @RequestParam Long studentId){
        Course course = courseService.getCourseById(courseId);
        Client student = clientService.getUserById(studentId);
        List<Chapter> chapters = course.getChapters();
        List<HwData> hwDataList = new ArrayList<>();
        for (Chapter chapter : chapters){
            Quiz quiz = chapter.getQuiz();
            if (quiz != null){
                HwData hwData = new HwData();
                hwData.setTitle(chapter.getName());
                QuizGradeBook quizGradeBook = quizGradeBookService.getByStudentAndQuiz(student, quiz);
                if (quizGradeBook != null){
                    hwData.setScore(String.valueOf(quizGradeBook.getGrade()));
                    Assignment assignment = chapter.getAssignment();
                    if (assignment != null){
                        AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getByStudentAndAssignment(student,assignment);
                        if (assignmentGradeBook != null && assignmentGradeBook.isRead()){
                            hwData.setState("已完成");
                        } else {
                            hwData.setState("未完成");
                        }
                    } else {
                        hwData.setState("未完成");
                    }
                } else {
                    hwData.setState("未完成");
                    hwData.setScore("-");
                }

                hwDataList.add(hwData);
            }
        }
        return hwDataList;
    }
}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class HwData{
    private String title;
    private String state;
    private String date;
    private String score;
    private String chapterId;

}


