package com.sustech.ooad.controller;

import com.sustech.ooad.entity.*;
import com.sustech.ooad.service.*;
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
import java.util.Objects;

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

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Transactional
    @ResponseBody
    public void excelOutPortChina(HttpServletResponse response,  @RequestParam Long chapterId) throws Exception {
        List<StudentScore> studentScores = util(chapterId);
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
            dataRow.createCell(1).setCellValue(data.getQuiz_score());
            dataRow.createCell(2).setCellValue(data.getHomework_score());
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


    @GetMapping("/list")
    @Transactional
    public List<StudentScore> getStudentScore(@RequestParam Long chapterId){
        return util(chapterId);
    }


    private List<StudentScore> util(Long chapterId){
        List<StudentScore> studentScores = new ArrayList<>();
        Chapter chapter = chapterService.getChapterById(chapterId);
        Course course = chapter.getCourse();
        List<Client> students = course.getClientsSubscribed();
        Quiz quiz = chapter.getQuiz();
        Assignment assignment = chapter.getAssignment();
        for (Client student : students){
            StudentScore studentScore = new StudentScore();
            studentScore.setName(student.getName());
            studentScore.setId(student.getId());

            QuizGradeBook quizGradeBook = quizGradeBookService.getByStudentAndQuiz(student, quiz);
            if (quizGradeBook == null){
                studentScore.setQuiz_score("未完成");
                studentScore.setQuiz_gradebook_id(-1L);
            } else {
                studentScore.setQuiz_score(String.valueOf(quizGradeBook.getGrade()));
                studentScore.setQuiz_gradebook_id(quizGradeBook.getId());
            }

            AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getByStudentAndAssignment(student, assignment);
            if (assignmentGradeBook == null){
                studentScore.setHomework_score("未提交");
                studentScore.setHomework_gradebook_id(-1L);
            } else if (assignmentGradeBook.isRead()){
                studentScore.setHomework_score(String.valueOf(assignmentGradeBook.getGrade()));
                studentScore.setHomework_gradebook_id(assignmentGradeBook.getId());
            } else {
                studentScore.setHomework_score("未批改");
                studentScore.setHomework_gradebook_id(assignmentGradeBook.getId());
            }
            studentScores.add(studentScore);
        }
        return studentScores;
    }

    @PostMapping("/updateQuizAndAssignment")
    @Transactional
    public void updateQuizAndAssignment(@RequestBody List<StudentScore> studentScores){
        for (StudentScore studentScore : studentScores){
            if (studentScore.getQuiz_gradebook_id() != -1){
                QuizGradeBook quizGradeBook = quizGradeBookService.getById(studentScore.getQuiz_gradebook_id());
                quizGradeBook.setGrade(Integer.parseInt(studentScore.getQuiz_score()));
            }
            if (studentScore.getHomework_gradebook_id() != -1 && !studentScore.getHomework_score().equals("未批改")){
                AssignmentGradeBook assignmentGradeBook = assignmentGradeBookService.getById(studentScore.getHomework_gradebook_id());
                assignmentGradeBook.setGrade(Integer.parseInt(studentScore.getHomework_score()));
                assignmentGradeBook.setRead(true);
            }
        }
    }


    @GetMapping("/studentData")
    @Transactional
    @ResponseBody
    public void excelOutStudentData(HttpServletResponse response, @RequestParam Long courseId) throws Exception {
        List<Client> studentScores = courseService.getCourseById(courseId).getClientsSubscribed();
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("学生信息表");
        // 创建表头
        HSSFRow hssfRow = sheet.createRow(0);
        hssfRow.createCell(0).setCellValue("学生姓名");
        hssfRow.createCell(1).setCellValue("学生邮箱");
        for (Client data : studentScores){
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getName());
            dataRow.createCell(1).setCellValue(data.getEmail());
        }
        // 建立输出流，输出浏览器文件
        OutputStream os = null;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + new String("学生信息".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
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
    private Long id;
    private String name;
    private String quiz_score;
    private String homework_score;
    private Long quiz_gradebook_id;
    private Long homework_gradebook_id;
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class StudentMessage{
    private String name;
    private String email;
}