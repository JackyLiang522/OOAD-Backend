package com.sustech.ooad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String email;

    private String password;

    private boolean isTeacher;
    private boolean isAdmin = false;

    private int account = 0;

    // 订阅课程
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "subscribed",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    nullable = false,
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    nullable = false,
                    referencedColumnName = "id"))
    private List<Course> coursesSubscribed = new ArrayList<>();


    // 购买课程
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "purchased",
        joinColumns = @JoinColumn(
                name = "client_id",
                nullable = false,
                referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
                name = "course_id",
                nullable = false,
                referencedColumnName = "id"))
    private List<Course> coursesPurchased = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> coursesCreated = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();



    public Client() {

    }

    public Client(@NotNull String name, String email, String password, boolean isTeacher,  boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isTeacher = isTeacher;
        this.isAdmin = isAdmin;
    }
}
