package com.rocketbuilder.schoolbase.models;

import com.rocketbuilder.schoolbase.enums.GroupDigit;
import com.rocketbuilder.schoolbase.enums.GroupLetter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="groups")
    private Set<Student> students;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    private GroupDigit groupDigit;
    private GroupLetter groupLetter;

    public Groups(Set<Student> students, Teacher teacher, GroupDigit groupDigit, GroupLetter groupLetter) {
        this.students = students;
        this.teacher = teacher;
        this.groupDigit = groupDigit;
        this.groupLetter = groupLetter;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public GroupDigit getGroupDigit() { return groupDigit; }
    public void setGroupDigit(GroupDigit groupDigit) { this.groupDigit = groupDigit; }

    public GroupLetter getGroupLetter() { return groupLetter; }
    public void setGroupLetter(GroupLetter groupLetter) { this.groupLetter = groupLetter; }
}
