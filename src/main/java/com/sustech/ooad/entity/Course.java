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
    private Long id;

    private String teacher;

    private String courseName;

    private int chapterCount;

    private String introduction;

    private int price = 0;

    /*
    0: under review
    1: approved
    2: deleted
     */
    private int status = 0;

    @ManyToMany(mappedBy = "coursesSubscribed")
    private List<Client> clientsSubscribed = new ArrayList<>();

    @ManyToMany(mappedBy = "coursesPurchased")
    private List<Client> clientsPurchased = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "course")
    private List<Announcement> announcements;


    public Course() {

    }

    public Course(String teacher, String name, String introduction, int price) {
        this.teacher = teacher;
        this.courseName = name;
        this.introduction = introduction;
        this.price = price;
    }
}
