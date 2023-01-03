package com.sustech.ooad.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @JsonIgnore
    @OneToOne()
    @JoinColumn(
            name = "chapter_id",
            nullable = false,
            referencedColumnName = "id")
    private Chapter chapter;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "assignment")
    private List<AssignmentGradeBook> assignmentGradeBooks = new ArrayList<>();


    public Assignment() {

    }

    public Assignment(String description) {
        this.description = description;
    }
}
