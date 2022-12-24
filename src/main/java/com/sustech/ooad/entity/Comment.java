package com.sustech.ooad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contents;

    private String nickname;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id")
    private Client user;


    private Date createTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "chapter_id",
            nullable = false,
            referencedColumnName = "id")
    private Chapter chapter;


    public Comment() {

    }

    public Comment( String contents, String nickname, Date createTime) {
        this.contents = contents;
        this.nickname = nickname;
        this.createTime = createTime;
    }

}

