package com.rocketbuilder.schoolbase.models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;

    private String firstname, surname, middlename;
    private String job;
    private String number;

    public Parent(Student student, String firstname, String surname, String middlename,  String job, String number) {
        this.student = student;
        this.firstname = firstname;
        this.middlename = middlename;
        this.surname = surname;
        this.job = job;
        this.number = number;
    }

    public Parent(String firstname, String surname, String middlename, String job, String number) {
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
        this.job = job;
        this.number = number;
    }

    public Parent() {}

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public String getNumber() { return number; };
    public void setNumber(String number) { this.number = number; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public String getMiddlename() { return middlename; }
    public void setMiddlename(String middlename) { this.middlename = middlename; }
}
