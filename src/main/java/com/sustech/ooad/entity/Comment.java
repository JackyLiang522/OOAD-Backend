package com.sustech.ooad.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    private String contents;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String username;


    private int userid;

    public Comment() {

    }

    public Comment(Chapter chapter, String contents, int id, String username, int userid) {
        this.chapter = chapter;
        this.contents = contents;
        this.id = id;
        this.username = username;
        this.userid = userid;
    }


}



