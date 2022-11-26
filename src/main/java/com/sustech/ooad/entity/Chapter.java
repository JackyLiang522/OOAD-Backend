package com.sustech.ooad.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Entity
@Getter
@Setter
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    private Long chapterNumber;

    public Chapter() {
    }

    public Chapter(String name, Long chapterNumber) {
        this.name = name;
        this.chapterNumber = chapterNumber;
    }
}
