package com.sustech.ooad.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    private Chapter chapter;


    @JsonIgnore
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
    private List<QuizProblem> quizProblems = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "quiz")
    private List<QuizGradeBook> quizGradeBooks = new ArrayList<>();

    public Quiz() {

    }

    public Quiz(Chapter chapter) {
        this.chapter = chapter;
    }
}
