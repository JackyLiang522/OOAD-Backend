package com.sustech.ooad.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter")
    private Chapter chapter;

    private String description;

    private String options;

    public Quiz() {

    }

    public Quiz(Chapter chapter, String description, String options) {
        this.chapter = chapter;
        this.description = description;
        this.options = options;
    }
}
