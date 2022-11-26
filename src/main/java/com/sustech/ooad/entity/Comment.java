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


    private String nickname;


    private int user_id;

    public Comment() {

    }

    public Comment(Chapter chapter, String contents, int id, String nickname, int user_id) {
        this.chapter = chapter;
        this.contents = contents;
        this.id = id;
        this.nickname = nickname;
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public String getContents() {
        return contents;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}



