package com.sustech.ooad.controller;

import com.sustech.ooad.entity.*;
import com.sustech.ooad.service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentGradeBookService assignmentGradeBookService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ClientService clientService;


    // http://localhost:8081/api/assignment/list?chapterId=
    @GetMapping("/list")
    @Transactional
    public FrontAssignment getAssignmentsByChapterId(@RequestParam Long chapterId){
        Chapter chapter = chapterService.findChapterById(chapterId);
        Assignment assignment = chapter.getAssignment();
        if (assignment != null){
            FrontAssignment frontAssignment = new FrontAssignment();
            frontAssignment.setTitle(assignment.getTitle());
            Date date = assignment.getDeadline();
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式
            String ddl = sformat.format(date);
            frontAssignment.setDeadline(ddl);
            return frontAssignment;
        } else {
            return null;
        }

    }

    // http://localhost:8081/api/assignment/add?chapterId=&&title=&&deadline=
    @PostMapping("/add")
    @Transactional
    public void addAssignment(@RequestParam Long chapterId, @RequestParam String title, @RequestParam String deadline) throws ParseException {
        Chapter chapter = chapterService.findChapterById(chapterId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = sdf.parse(deadline);
        if (chapter.getAssignment() == null){
            Assignment assignment = new Assignment(title, date);
            chapter.setAssignment(assignment);
            assignment.setChapter(chapter);
            assignmentService.saveAssignment(assignment);
        } else {
            Assignment assignment = chapter.getAssignment();
            assignment.setTitle(title);
            assignment.setDeadline(date);
        }

    }

    // http://localhost:8081/api/assignment/recordGrade?chapterId=&&studentId=&&grade=
    @PostMapping("/recordGrade")
    @Transactional
    public void recordGrade(@RequestParam Long assignmentGradeBookId,  @RequestParam int grade){
        AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getById(assignmentGradeBookId);
        assignmentGradeBook.setGrade(grade);
        assignmentGradeBook.setRead(true);
    }

    // 提交作业之后生成 grade book
    // submit
    @PostMapping("/submit")
    @Transactional
    public void submitAssignment(@RequestParam Long assignmentId, @RequestParam Long studentId){
        Assignment assignment = assignmentService.getById(assignmentId);
        Client student = clientService.getUserById(studentId);
        if (assignmentGradeBookService.getByStudentAndAssignment(student, assignment) == null){
            AssignmentGradeBook assignmentGradeBook = new AssignmentGradeBook(assignment,student);
            assignment.getAssignmentGradeBooks().add(assignmentGradeBook);
            student.getAssignmentGradeBooks().add(assignmentGradeBook);
            assignmentGradeBookService.save(assignmentGradeBook);
        } else {
            AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getByStudentAndAssignment(student, assignment);
            assignmentGradeBook.setRead(false);
        }
    }

    @GetMapping("/showHomeWork")
    @Transactional
    public FrontHomeWork showHomeWork(@RequestParam long chapterId, @RequestParam long studentId){
        Chapter chapter = chapterService.getChapterById(chapterId);
        Client student = clientService.getUserById(studentId);
        Assignment assignment = chapter.getAssignment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (assignment != null){
            FrontHomeWork frontHomeWork = new FrontHomeWork();
            frontHomeWork.setTitle(assignment.getTitle());
            frontHomeWork.setDeadline(sdf.format(assignment.getDeadline()));
            AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getByStudentAndAssignment(student, assignment);
            if (assignmentGradeBook != null){
                if (assignmentGradeBook.isRead()){
                    frontHomeWork.setScore(String.valueOf(assignmentGradeBook.getGrade()));
                    frontHomeWork.setState("已完成");
                } else {
                    frontHomeWork.setScore("");
                    frontHomeWork.setState("未批改");
                }
            } else {
                frontHomeWork.setScore("");
                frontHomeWork.setState("未提交");
            }
            return frontHomeWork;
        } else {
            return null;
        }

    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class FrontAssignment{
    private String title;
    private String deadline;
}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class FrontHomeWork{
    private String title;
    private String deadline;
    private String state;
    private String score;
}
