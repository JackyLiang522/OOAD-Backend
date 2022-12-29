package com.sustech.ooad.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "chapter_id",
            nullable = false,
            referencedColumnName = "id")
    private Chapter chapter;

    private String description;

    public Assignment() {
    }

    public Assignment(String description) {
        this.description = description;
    }
}
