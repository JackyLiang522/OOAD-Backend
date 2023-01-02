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
    private int id;

    @JsonIgnore
    @OneToOne
    private Chapter chapter;


    @JsonIgnore
    @OneToMany(mappedBy = "quiz")
    private List<QuizProblem> quizProblems = new ArrayList<>();

    public Quiz() {

    }

    public Quiz(Chapter chapter) {
        this.chapter = chapter;
    }
}
