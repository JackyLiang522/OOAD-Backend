package com.sustech.ooad.entity;

import java.util.ArrayList;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "teacher_id",
            nullable = false,
            referencedColumnName = "id")
    private Client teacher;

    private String courseName;


    private String introduction;

    private int price = 0;

    /*
    0: under review
    1: approved
    2: deleted
     */
    private int status = 0;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "coursesSubscribed")
    private List<Client> clientsSubscribed = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Announcement> announcements = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Chapter> chapters = new ArrayList<>();


    public Course() {

    }

    public Course(Client teacher, String name, String introduction, int price) {
        this.teacher = teacher;
        this.courseName = name;
        this.introduction = introduction;
        this.price = price;
    }
}
