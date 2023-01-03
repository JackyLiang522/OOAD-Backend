package com.sustech.ooad.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "quiz_grade_book")
public class QuizGradeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int grade;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "client_id",
            nullable = false,
            referencedColumnName = "id")
    private Client student;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "quiz_id",
            nullable = false,
            referencedColumnName = "id")
    private Quiz quiz;

    public QuizGradeBook() {

    }

    public QuizGradeBook(int grade, Client student, Quiz quiz) {
        this.grade = grade;
        this.student = student;
        this.quiz = quiz;
    }
}
