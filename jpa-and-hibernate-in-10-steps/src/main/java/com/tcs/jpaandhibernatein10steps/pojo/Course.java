package com.tcs.jpaandhibernatein10steps.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Course {
    @Id
    Long id;
//    @Column(name="courseName")
    String courseName;
//    @Column(name="authorName")
    String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Course() {
    }

    public Course(Long id, String courseName, String author) {

        this.id = id;
        this.courseName = courseName;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + courseName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
