package com.sustech.ooad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "assignment_grade_book")
public class AssignmentGradeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "assignment_id",
            nullable = false,
            referencedColumnName = "id")
    private Assignment assignment;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "client_id",
            nullable = false,
            referencedColumnName = "id")
    private Client student;

    private int grade = -1;

    private boolean isRead = false;

    public AssignmentGradeBook(Assignment assignment, Client student, int grade) {
        this.assignment = assignment;
        this.student = student;
    }

    public AssignmentGradeBook() {

    }

}
