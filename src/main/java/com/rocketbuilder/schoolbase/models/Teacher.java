package com.rocketbuilder.schoolbase.models;


import com.rocketbuilder.schoolbase.enums.UserRole;

import javax.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Groups groups;

    private String firstname, surname, middlename;
    private String number;
    private String password;
    private String login;

    private UserRole role = UserRole.TEACHER;

    public Teacher() {}

    public Teacher(Groups group, String firstname, String surname, String middlename, String number, String login, String password) {
        this.groups = group;
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
        this.number = number;
        this.login = login;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Groups getGroups() { return groups; }
    public void setGroups(Groups groups) { this.groups = groups; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getMiddlename() { return middlename; }
    public void setMiddlename(String middlename) { this.middlename = middlename; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
