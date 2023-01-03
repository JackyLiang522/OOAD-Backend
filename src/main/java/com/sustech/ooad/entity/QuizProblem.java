package com.sustech.ooad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "quiz_problem")
public class QuizProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "quiz_id",
            nullable = false,
            referencedColumnName = "id")
    private Quiz quiz;

    private String description;

    private String type;

    private String options;

    private String answer;



    public QuizProblem(Quiz quiz, String description, String type, String options, String answer) {
        this.quiz = quiz;
        this.description = description;
        this.options = options;
        this.answer = answer;
        this.type = type;
    }

    public QuizProblem(Quiz quiz, String description, String type) {
        this.quiz = quiz;
        this.description = description;
        this.type = type;
    }

    public QuizProblem() {

    }
}
