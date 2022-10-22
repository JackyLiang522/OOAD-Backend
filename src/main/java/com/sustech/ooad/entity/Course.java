package com.sustech.ooad.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String teacher;

    private String name;

    private int chapter_count;

    private String introduction;

    private int price;


    // @ManyToMany(targetEntity = Client.class)
    // @JoinTable(name = "clients")
    @ManyToMany(mappedBy = "courses")
    private List<Client> clients;


    public Course() {

    }

    public Course(int id, String teacher, String name, int chapter_count, String introduction, int price) {
        this.id = id;
        this.teacher = teacher;
        this.name = name;
        this.chapter_count = chapter_count;
        this.introduction = introduction;
        this.price = price;
    }


}
