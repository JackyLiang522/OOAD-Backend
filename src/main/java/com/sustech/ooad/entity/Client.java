package com.sustech.ooad.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    private String email;

    private String password;

    private boolean is_teacher;

    private boolean is_admin;

    @ManyToMany
    @JoinTable(
            name = "course_clients",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<>();

    public Client() {
    }

    public Client(@NotNull String name, String email, String password, boolean is_teacher, boolean is_admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.is_teacher = is_teacher;
        this.is_admin = is_admin;
    }

    public String getPassword() {
        return password;
    }
}
