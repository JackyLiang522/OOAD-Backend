package com.sustech.ooad.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;

    private String email;

    private String password;

    private boolean is_student;

    private boolean is_teacher;

    private boolean is_admin;

    @ManyToMany
    @JoinTable(
            name = "course_clients",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    public Client() {
    }

    public Client(int id, @NotNull String name, String email, String password, boolean is_student, boolean is_teacher, boolean is_admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.is_student = is_student;
        this.is_teacher = is_teacher;
        this.is_admin = is_admin;
    }

    public String getPassword() {
        return password;
    }
}
