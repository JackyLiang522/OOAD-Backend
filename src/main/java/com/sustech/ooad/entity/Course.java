package com.sustech.ooad.entity;

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

    private int price;

    @ManyToMany(mappedBy = "courses")
    private List<Client> clients;

    @ManyToMany(mappedBy = "courses")
    private List<Chapter> chapters;


    public Course() {

    }

    public Course(int id, String teacher, String name, int chapterCount, String introduction, int price) {
        this.id = id;
        this.teacher = teacher;
        this.name = name;
        this.chapterCount = chapterCount;
        this.introduction = introduction;
        this.price = price;
    }


}
