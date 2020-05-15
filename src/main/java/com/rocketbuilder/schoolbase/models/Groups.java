package com.rocketbuilder.schoolbase.models;

import com.rocketbuilder.schoolbase.enums.GroupDigit;
import com.rocketbuilder.schoolbase.enums.GroupLetter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="groups")
    private Set<Student> students = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    private String title;

    public Groups(Set<Student> students, Teacher teacher, String title) {
        this.students = students;
        this.teacher = teacher;
        this.title = title;
    }

    public Groups(Teacher teacher, String title) {
        this.teacher = teacher;
        this.title = title;
    }

    public Groups(String title) {
        this.title = title;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
