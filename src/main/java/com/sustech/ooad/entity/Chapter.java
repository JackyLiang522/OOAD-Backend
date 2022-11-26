package com.sustech.ooad.entity;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "course_chapters",
        joinColumns = @JoinColumn(name = "chapter_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @OneToMany(mappedBy = "chapter")
    private List<Comment> comments = new ArrayList<>();

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
