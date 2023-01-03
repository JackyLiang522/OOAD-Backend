package com.sustech.ooad.controller;

import com.sustech.ooad.entity.*;
import com.sustech.ooad.service.AssignmentGradeBookService;
import com.sustech.ooad.service.ChapterService;
import com.sustech.ooad.service.CourseService;
import com.sustech.ooad.service.QuizGradeBookService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private QuizGradeBookService quizGradeBookService;

    @Autowired
    private AssignmentGradeBookService assignmentGradeBookService;

    @GetMapping("")
    @Transactional
    @ResponseBody
    public void excelOutPortChina(HttpServletResponse response, @RequestParam Long courseId, @RequestParam Long chapterId) throws Exception {
        List<StudentScore> studentScores = new ArrayList<>();
        Course course = courseService.getCourseById(courseId);
        List<Client> students = course.getClientsSubscribed();
        Chapter chapter = chapterService.getChapterById(chapterId);
        Quiz quiz = chapter.getQuiz();
        Assignment assignment = chapter.getAssignment();
        for (Client student : students){
            StudentScore studentScore = new StudentScore();
            studentScore.setName(student.getName());

            QuizGradeBook quizGradeBook = quizGradeBookService.getByStudentAndQuiz(student, quiz);
            if (quizGradeBook == null){
                studentScore.setQuiz("未完成");
            } else {
                studentScore.setQuiz(String.valueOf(quizGradeBook.getGrade()));
            }

            AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getByStudentAndAssignment(student, assignment);
            if (assignmentGradeBook == null){
                studentScore.setAssignment("未提交");
            } else if (assignmentGradeBook.isRead()){
                studentScore.setAssignment(String.valueOf(assignmentGradeBook.getGrade()));
            } else {
                studentScore.setAssignment("未批改");
            }
            studentScores.add(studentScore);
        }
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("成绩单");
        // 创建表头
        HSSFRow hssfRow = sheet.createRow(0);
        hssfRow.createCell(0).setCellValue("学生姓名");
        hssfRow.createCell(1).setCellValue("测验成绩");
        hssfRow.createCell(2).setCellValue("作业成绩");
        for (StudentScore data : studentScores){
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getName());
            dataRow.createCell(1).setCellValue(data.getQuiz());
            dataRow.createCell(2).setCellValue(data.getAssignment());
        }
        // 建立输出流，输出浏览器文件
        OutputStream os = null;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + new String("学生成绩单".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
        // 输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();

    }
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class StudentScore{
    private String name;
    private String quiz;
    private String assignment;
}
