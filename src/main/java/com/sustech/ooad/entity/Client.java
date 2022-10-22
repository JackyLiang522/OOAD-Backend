package com.sustech.ooad.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;

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

    public Client() {
    }

    public Client(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
