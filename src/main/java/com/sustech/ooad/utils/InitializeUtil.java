package com.sustech.ooad.utils;

import com.sustech.ooad.entity.*;
import com.sustech.ooad.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InitializeUtil {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private AssignmentGradeBookRepository assignmentGradeBookRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private QuizGradeBookRepository quizGradeBookRepository;
    @Autowired
    private QuizProblemRepository quizProblemRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    public void initialize() {
        Client teacher1 = new Client("林雨航", "12010903@mail.sustech.edu.cn", "123", true, false),
                teacher2 = new Client("曾宪清", "12012338@mail.sustech.edu.cn", "123", true, false),
                admin1 = new Client("梁家源", "11910504@mail.sustech.edu.cn", "123", false, true),
                student1 = new Client("陈文雁", "12012437@mail.sustech.edu.cn", "123", false, false),
                student2 = new Client("黄弋骞", "12012911@mail.sustech.edu.cn", "123", false, false);
        clientRepository.saveAll(Arrays.asList(teacher1, teacher2, admin1, student1, student2));

        Course course1 = new Course(
                teacher1,
                "面向对象分析与设计",
                "本课程在介绍面向对象的基本原理、统一建模语言UML的基础上，主要讲述面向对象的需求获取、系统分析、系统设计、设计原则、设计模式、实现方法以及测试。",
                0
        ),
                course2 = new Course(
                        teacher1,
                        "人工智能导论",
                        "本课程详述人工智能的基础概念，介绍人工神经元网络以及现在流行的基于数据驱动的深度学习人工智能网络模型，算法，平台。",
                        10
                ),
                course3 = new Course(
                        teacher1,
                        "创新实践I",
                        "在科研导师的指导下，给本科生提供运用计算机专业知识解决实际问题的科研创新活动平台。培养学生运用不同技能以及学科知识解决现实中实际问题的能力。",
                        3
                ),
                course4 = new Course(
                        teacher1,
                        "计算机系统设计及应用A",
                        "这门课程主要用java语言介绍比较高级的面向对象编程技术，包括网络编程及多线程。学生应该熟悉java编程环境及相关APIs。通过这门课程，学生可以具备开发一款可以实际应用软件的能力。",
                        5
                ),
                course5 = new Course(
                        teacher2,
                        "阿拉伯世界的1500年",
                        "本课程共分十五个主题，大致对应阿拉伯世界的十五个世纪，涵盖伊斯兰教产生之前的阿拉伯半岛概况、阿拉伯帝国的迅速扩张、黄金时代阿拉伯文化的世界影响、及近现代为摆脱殖民压迫的阿拉伯民族觉醒等内容。",
                        10
                );
        course1.setStatus(1);
        course2.setStatus(1);
        course3.setStatus(1);
        course4.setStatus(0);
        course5.setStatus(2);
        courseRepository.saveAll(Arrays.asList(course1, course2, course3, course4, course5));
        teacher1.getCoursesCreated().addAll(Arrays.asList(course1, course2, course3, course4));
        teacher2.getCoursesCreated().add(course5);

        Chapter chapter1_course1 = new Chapter("UML 模型 1"),
                chapter2_course1 = new Chapter("UML 模型 2"),
                chapter3_course1 = new Chapter("信息隐藏"),
                chapter1_course2 = new Chapter("简介：定义和问题"),
                chapter1_course3 = new Chapter("开题报告"),
                chapter1_course4 = new Chapter("计算，数据类型和数据 I / O"),
                chapter1_course5 = new Chapter("阿拉伯铜手");
        chapter1_course1.setCourse(course1);
        chapter2_course1.setCourse(course1);
        chapter3_course1.setCourse(course1);
        chapter1_course2.setCourse(course2);
        chapter1_course3.setCourse(course3);
        chapter1_course4.setCourse(course4);
        chapter1_course5.setCourse(course5);
        course1.getChapters().addAll(Arrays.asList(
                chapter1_course1,chapter2_course1,chapter3_course1
        ));
        course2.getChapters().add(chapter1_course2);
        course3.getChapters().add(chapter1_course3);
        course4.getChapters().add(chapter1_course4);
        course5.getChapters().add(chapter1_course5);
        chapterRepository.saveAll(Arrays.asList(chapter1_course1, chapter2_course1, chapter3_course1,
                chapter1_course2, chapter1_course3, chapter1_course4, chapter1_course5));

        Announcement announcement1_course1 = new Announcement(
                "第16周实验课答辩调课至17周的通知",
                "各位同学，\n" +
                "由于大部分小组申请1月6日答辩，因此，原计划第16周的实验课调整到第17周（周五34，56，78，周六34），这四个时间段都用于项目答辩，具体安排如下：\n" +
                "https://docs.qq.com/sheet/DVmlpa0VkaEFtT0pZ?tab=4ztx19\n" +
                "每组15分钟，13分钟描述，2分钟问答，每过3-4组会有5-10分钟的缓冲时间\n" +
                "答辩小组较多，请大家安排好时间。"
        ),
                announcement2_course1 = new Announcement(
                        "OOAD项目贡献比统计通知",
                        "各位同学：\n" +
                        "项目贡献比默认是：5人组每人20，4人组每人25。如果部分小队项目贡献度严重不均衡，可以申请贡献比调整。\n" +
                        "贡献比调整以链接中的计算方法为准。\n" +
                        "https://docs.qq.com/sheet/DVmlpa0VkaEFtT0pZ?tab=ncnrjj"
                ),
                announcement3_course1 = new Announcement(
                        "OOAD期末考察要求已发布",
                        "各位同学，\n" +
                        "本门课程的期末考察文档已发布，请在Assignments中查看，最后将报告提交到sakai中的Assingment里。\n" +
                        "考察作业仅对当前在教务系统中报名二级制的同学开放，请大家注意截止日期：2023年1月10日晚10:00，如若延期提交会有90%分数折扣。\n" +
                        "请悉知。"
                );
        announcement1_course1.setCourse(course1);
        announcement2_course1.setCourse(course1);
        announcement3_course1.setCourse(course1);
        course1.getAnnouncements().addAll(Arrays.asList(announcement1_course1, announcement2_course1, announcement3_course1));
        announcementRepository.saveAll(Arrays.asList(announcement1_course1, announcement2_course1, announcement3_course1));

        Calendar calendar1 = Calendar.getInstance(), calendar2 = Calendar.getInstance(), calendar3 = Calendar.getInstance();
        calendar1.set(2022, Calendar.SEPTEMBER, 28, 23, 0, 0);
        calendar2.set(2023, Calendar.DECEMBER, 12, 23, 55, 0);
        calendar3.set(2023, Calendar.JANUARY, 1, 20, 0, 0);
        Assignment assignment1_course1 = new Assignment("assignment1 UML", calendar1.getTime()),
                assignment2_course1 = new Assignment("Assignment4 DI", calendar2.getTime()),
                assignment3_course1 = new Assignment("Project最终代码提交", calendar3.getTime());
        assignment1_course1.setChapter(chapter1_course1);
        assignment2_course1.setChapter(chapter2_course1);
        assignment3_course1.setChapter(chapter3_course1);
        chapter1_course1.setAssignment(assignment1_course1);
        chapter2_course1.setAssignment(assignment2_course1);
        chapter3_course1.setAssignment(assignment3_course1);
        assignmentRepository.saveAll(Arrays.asList(assignment1_course1, assignment2_course1, assignment3_course1));

        AssignmentGradeBook
                assignmentGradeBook_assi1_stu1 = new AssignmentGradeBook(assignment1_course1, student1),
                assignmentGradeBook_assi1_stu2 = new AssignmentGradeBook(assignment1_course1, student2),
                assignmentGradeBook_assi2_stu1 = new AssignmentGradeBook(assignment2_course1, student1),
                assignmentGradeBook_assi2_stu2 = new AssignmentGradeBook(assignment2_course1, student2),
                assignmentGradeBook_assi3_stu1 = new AssignmentGradeBook(assignment3_course1, student1);
        assignmentGradeBook_assi1_stu1.setGrade(100);
        assignmentGradeBook_assi1_stu2.setGrade(100);
        assignmentGradeBook_assi2_stu1.setGrade(80);
        assignmentGradeBook_assi2_stu2.setGrade(60);
        assignmentGradeBook_assi3_stu1.setGrade(0);
        assignmentGradeBookRepository.saveAll(Arrays.asList(
                assignmentGradeBook_assi1_stu1,
                assignmentGradeBook_assi1_stu2,
                assignmentGradeBook_assi2_stu1,
                assignmentGradeBook_assi2_stu2,
                assignmentGradeBook_assi3_stu1
        ));
        student1.getAssignmentGradeBooks().addAll(Arrays.asList(assignmentGradeBook_assi1_stu1, assignmentGradeBook_assi2_stu1, assignmentGradeBook_assi3_stu1));
        student2.getAssignmentGradeBooks().addAll(Arrays.asList(assignmentGradeBook_assi1_stu2, assignmentGradeBook_assi2_stu2));
        assignment1_course1.getAssignmentGradeBooks().addAll(Arrays.asList(assignmentGradeBook_assi1_stu1, assignmentGradeBook_assi1_stu2));
        assignment2_course1.getAssignmentGradeBooks().addAll(Arrays.asList(assignmentGradeBook_assi2_stu1, assignmentGradeBook_assi2_stu2));
        assignment3_course1.getAssignmentGradeBooks().add(assignmentGradeBook_assi3_stu1);

        Calendar calendar1_comment = Calendar.getInstance(),
                calendar2_comment = Calendar.getInstance(),
                calendar3_comment = Calendar.getInstance(),
                calendar4_comment = Calendar.getInstance(),
                calendar5_comment = Calendar.getInstance();
        calendar1_comment.set(2022, Calendar.SEPTEMBER, 28, 23, 10, 10);
        calendar2_comment.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
        calendar3_comment.set(2022, Calendar.DECEMBER, 1, 1, 1, 1);
        calendar4_comment.set(2023, Calendar.JANUARY, 1, 2, 2, 2);
        calendar5_comment.set(2021, Calendar.JANUARY, 30, 8, 8, 8);
        Comment comment1_chapter1_course1_stu1 = new Comment(
                "讲得很好，下次还来",
                student1.getName(),
                calendar1_comment.getTime()
        ),
                comment2_chapter1_course1_stu1 = new Comment(
                        "又来了",
                        student1.getName(),
                        calendar2_comment.getTime()
                ),
                comment_chapter1_course1_tcr1 = new Comment(
                        "教师发一个评论",
                        teacher1.getName(),
                        calendar3_comment.getTime()
                ),
                comment_chapter2_course1_stu1 = new Comment(
                        "前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观前来围观",
                        student1.getName(),
                        calendar4_comment.getTime()
                ),
                comment_chapter2_course1_stu2 = new Comment(
                        "来了",
                        student2.getName(),
                        calendar5_comment.getTime()
                );
        chapter1_course1.getComments().addAll(Arrays.asList(
                comment1_chapter1_course1_stu1,
                comment2_chapter1_course1_stu1,
                comment_chapter1_course1_tcr1
        ));
        chapter2_course1.getComments().addAll(Arrays.asList(
                comment_chapter2_course1_stu1,
                comment_chapter2_course1_stu2
        ));
        comment1_chapter1_course1_stu1.setChapter(chapter1_course1);
        comment2_chapter1_course1_stu1.setChapter(chapter1_course1);
        comment_chapter1_course1_tcr1.setChapter(chapter1_course1);
        comment_chapter2_course1_stu1.setChapter(chapter2_course1);
        comment_chapter2_course1_stu2.setChapter(chapter2_course1);
        comment1_chapter1_course1_stu1.setUser(student1);
        comment2_chapter1_course1_stu1.setUser(student1);
        comment_chapter1_course1_tcr1.setUser(teacher1);
        comment_chapter2_course1_stu1.setUser(student1);
        comment_chapter2_course1_stu2.setUser(student2);
        commentRepository.saveAll(Arrays.asList(
                comment1_chapter1_course1_stu1,
                comment2_chapter1_course1_stu1,
                comment_chapter1_course1_tcr1,
                comment_chapter2_course1_stu1,
                comment_chapter2_course1_stu2
        ));
        student1.getComments().addAll(Arrays.asList(comment_chapter2_course1_stu1, comment1_chapter1_course1_stu1, comment2_chapter1_course1_stu1));
        teacher1.getComments().add(comment_chapter1_course1_tcr1);
        student2.getComments().add(comment_chapter2_course1_stu2);

        Quiz quiz_chapter1_course1 = new Quiz(chapter1_course1),
                quiz_chapter2_course1 = new Quiz(chapter2_course1),
                quiz_chapter3_course1 = new Quiz(chapter3_course1),
                quiz_chapter1_course2 = new Quiz(chapter1_course2),
                quiz_chapter1_course3 = new Quiz(chapter1_course3),
                quiz_chapter1_course4 = new Quiz(chapter1_course4),
                quiz_chapter1_course5 = new Quiz(chapter1_course5);
        chapter1_course1.setQuiz(quiz_chapter1_course1);
        chapter2_course1.setQuiz(quiz_chapter2_course1);
        chapter3_course1.setQuiz(quiz_chapter3_course1);
        chapter1_course2.setQuiz(quiz_chapter1_course2);
        chapter1_course3.setQuiz(quiz_chapter1_course3);
        chapter1_course4.setQuiz(quiz_chapter1_course4);
        chapter1_course5.setQuiz(quiz_chapter1_course5);
        quizRepository.saveAll(Arrays.asList(
                quiz_chapter1_course1,
                quiz_chapter2_course1,
                quiz_chapter3_course1,
                quiz_chapter1_course2,
                quiz_chapter1_course3,
                quiz_chapter1_course4,
                quiz_chapter1_course5
        ));


        QuizProblem problem1_chapter1_course1 = new QuizProblem(
                quiz_chapter1_course1,
                "Star软件公司开发一个图像后期制作程序，该程序的主要功能是在原始的图片基础上可选择地调节对比度、反转、拉伸等操作。系统设计师Bob在设计系统时，想使对图片的每一步加工都都可逆，并不会影响到原始的图片，那么Bob应该使用哪个设计模式？",
                "单选",
                "观察者模式|状态模式|单例模式|装饰者模式|",
                "4|"
        ),
                problem2_chapter1_course1 = new QuizProblem(
                        quiz_chapter1_course1,
                        "Sea软件公司打算开发一个网络防火墙，该系统能够检测网络入侵和磁盘病毒，该系统的内核的启动要用20秒左右的时间，并要占用30M左右的内存空间。系统设计师Bob在设计该系统时，为了避免内核对象不会重复创建，那么不应该使用哪个设计模式？",
                        "多选",
                        "命令模式|策略模式|单例模式|适配器模式|",
                        "1|2|4|"
                ),
                problem3_chapter1_course1 = new QuizProblem(
                        quiz_chapter1_course1,
                        "⾯向对象的特点主要概括为抽象性、继承性、封装性、多态性。",
                        "判断",
                        "对|错|",
                        "1|"
                ),
                problem1_chapter2_course1 = new QuizProblem(
                        quiz_chapter2_course1,
                        "River软件公司开发一个Web服务器，该服务器能够根据客户端的请求，执行相应的处理，还可以对同时到达的请求排队，并对成功执行的每个请求记录日志。系统设计师Bob在设计该系统时，应该使用哪个设计模式以更好地支持对请求的处理？",
                        "单选",
                        "命令模式|观察者模式|适配器模式|外观模式|",
                        "1|"
                ),
                problem1_chapter3_course1 = new QuizProblem(
                        quiz_chapter3_course1,
                        "NextGen POS系统需要支持多种第三方外部服务，其中包括税金计算、信用卡授权、库存系统、账务系统。他们都具有不同的API，而且还无法改变。采用（）方法解决NextGen POS的系统设计比较适合",
                        "单选",
                        "装饰器模式|适配器模式|观察者模式|命令模式|",
                        "2|"
                ),
                problem1_chapter1_course2 = new QuizProblem(
                        quiz_chapter1_course2,
                        "单选",
                        "观察者模式表述错误的是",
                        "观察者角色的更新是被动的|被观察者可以通知观察者进行更新|观察者可以改变被观察者的状态，再由被观察者通知所有观察者依据被观察者的状态进行|",
                        "3|"
                ),
                problem1_chapter1_course3 = new QuizProblem(
                        quiz_chapter1_course3,
                        "单选",
                        "Jerry看到Tom睡觉了，他就会出来寻找奶酪；如果Tom醒了，Jerry就会回到洞中。请问采用何种设计模式描述该场景比较合适",
                        "装饰器模式|适配器模式|观察者模式|命令模式|",
                        "3|"
                ),
                problem1_chapter1_course4 = new QuizProblem(
                        quiz_chapter1_course4,
                        "单选",
                        "公司要为客户开发一个网站用来发布产品信息和客户沟通，那么该公司的系统分析人员，最好采用UML中的（）进行功能分析与建模",
                        "用例图|状态图|类图|活动图|",
                        "1|"
                ),
                problem1_chapter1_course5 = new QuizProblem(
                        quiz_chapter1_course5,
                        "单选",
                        "对于ATM机进动态建模，UML中（）起关键作用",
                        "用例图|活动图|状态图|顺序图|",
                        "3|"
                );
        quizProblemRepository.saveAll(Arrays.asList(
                problem1_chapter1_course1,
                problem2_chapter1_course1,
                problem3_chapter1_course1,
                problem1_chapter2_course1,
                problem1_chapter3_course1,
                problem1_chapter1_course2,
                problem1_chapter1_course3,
                problem1_chapter1_course4,
                problem1_chapter1_course5
        ));
        quiz_chapter1_course1.getQuizProblems().addAll(Arrays.asList(
                problem1_chapter1_course1,
                problem2_chapter1_course1,
                problem3_chapter1_course1
        ));
        quiz_chapter2_course1.getQuizProblems().add(problem1_chapter2_course1);
        quiz_chapter3_course1.getQuizProblems().add(problem1_chapter3_course1);
        quiz_chapter1_course2.getQuizProblems().add(problem1_chapter1_course2);
        quiz_chapter1_course3.getQuizProblems().add(problem1_chapter1_course3);
        quiz_chapter1_course4.getQuizProblems().add(problem1_chapter1_course4);
        quiz_chapter1_course5.getQuizProblems().add(problem1_chapter1_course5);

        QuizGradeBook
                quizGradeBook_stu1_chapter1_course1 = new QuizGradeBook(100, student1, quiz_chapter1_course1),
                quizGradeBook_stu2_chapter1_course1 = new QuizGradeBook(100, student2, quiz_chapter1_course1),
                quizGradeBook_tcr1_chapter1_course1 = new QuizGradeBook(100, teacher1, quiz_chapter1_course1),
                quizGradeBook_tcr2_chapter1_course1 = new QuizGradeBook(90, teacher2, quiz_chapter1_course1),
                quizGradeBook_stu1_chapter2_course1 = new QuizGradeBook(90, student1, quiz_chapter2_course1),
                quizGradeBook_stu2_chapter2_course1 = new QuizGradeBook(80, student2, quiz_chapter2_course1),
                quizGradeBook_stu1_chapter3_course1 = new QuizGradeBook(100, student1, quiz_chapter3_course1);
        quizGradeBookRepository.saveAll(Arrays.asList(
                quizGradeBook_stu1_chapter1_course1,
                quizGradeBook_stu2_chapter1_course1,
                quizGradeBook_tcr1_chapter1_course1,
                quizGradeBook_tcr2_chapter1_course1,
                quizGradeBook_stu1_chapter2_course1,
                quizGradeBook_stu2_chapter2_course1,
                quizGradeBook_stu1_chapter3_course1
        ));
        student1.getQuizGradeBooks().addAll(Arrays.asList(
                quizGradeBook_stu1_chapter1_course1,
                quizGradeBook_stu1_chapter2_course1,
                quizGradeBook_stu1_chapter3_course1
        ));
        student2.getQuizGradeBooks().addAll(Arrays.asList(
                quizGradeBook_stu2_chapter1_course1,
                quizGradeBook_stu2_chapter2_course1
        ));
        teacher1.getQuizGradeBooks().add(quizGradeBook_tcr1_chapter1_course1);
        teacher2.getQuizGradeBooks().add(quizGradeBook_tcr2_chapter1_course1);
        quiz_chapter1_course1.getQuizGradeBooks().addAll(Arrays.asList(
                quizGradeBook_stu1_chapter1_course1,
                quizGradeBook_stu2_chapter1_course1,
                quizGradeBook_tcr1_chapter1_course1,
                quizGradeBook_tcr2_chapter1_course1
        ));
        quiz_chapter2_course1.getQuizGradeBooks().addAll(Arrays.asList(
                quizGradeBook_stu1_chapter2_course1,
                quizGradeBook_stu2_chapter2_course1));
        quiz_chapter3_course1.getQuizGradeBooks().add(quizGradeBook_stu1_chapter3_course1);

        Calendar calendar_record1 = Calendar.getInstance(),
                calendar_record2 = Calendar.getInstance(),
                calendar_record3 = Calendar.getInstance(),
                calendar_record4 = Calendar.getInstance(),
                calendar_record5 = Calendar.getInstance();
        calendar_record1.set(2023, Calendar.JANUARY, 5, 10, 10, 10);
        calendar_record2.set(2023, Calendar.JANUARY, 5, 11, 10, 10);
        calendar_record3.set(2023, Calendar.JANUARY, 5, 12, 10, 10);
        calendar_record4.set(2023, Calendar.JANUARY, 5, 13, 10, 10);
        calendar_record5.set(2023, Calendar.JANUARY, 5, 14, 10, 10);
        TransactionRecord record1_stu1 = new TransactionRecord(
                10,
                10,
                student1,
                calendar_record1.getTime(),
                ""
        ),
                record2_stu1 = new TransactionRecord(
                        20,
                        10,
                        student1,
                        calendar_record2.getTime(),
                        ""
                ),
                record3_stu1 = new TransactionRecord(
                        20,
                        -10,
                        student1,
                        calendar_record3.getTime(),
                        "人工智能导论"
                ),
                record4_stu1 = new TransactionRecord(
                        30,
                        10,
                        student1,
                        calendar_record4.getTime(),
                        ""
                ),
                record5_stu1 = new TransactionRecord(
                        30,
                        0,
                        student1,
                        calendar_record5.getTime(),
                        "面向对象设计与分析"
                );
        transactionRecordRepository.saveAll(Arrays.asList(
                record1_stu1, record2_stu1, record3_stu1, record4_stu1, record5_stu1
        ));
        student1.getTransactionRecords().addAll(Arrays.asList(
                record1_stu1,
                record2_stu1,
                record3_stu1,
                record4_stu1,
                record5_stu1
        ));
        student1.getCoursesSubscribed().addAll(Arrays.asList(course1, course2));
        course1.getClientsSubscribed().add(student1);
        course2.getClientsSubscribed().add(student1);
    }
}