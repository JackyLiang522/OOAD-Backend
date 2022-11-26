package com.sustech.ooad.entity;

import java.util.ArrayList;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String teacher;

    private String name;

    private int chapterCount;

    private String introduction;

    private int price = 0;

    private int status = 0;

    @ManyToMany(mappedBy = "courses")
    private List<Client> clients = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Chapter> chapters;


    public Course() {

    }

    public Course(String teacher, String name, String introduction, int price) {
        this.teacher = teacher;
        this.name = name;
        this.introduction = introduction;
        this.price = price;
    }
}
