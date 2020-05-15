package com.rocketbuilder.schoolbase.models;


import com.rocketbuilder.schoolbase.enums.UserRole;

import javax.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "teacher")
    private Groups groups;

    private String firstname, surname, middlename;
    private String number;
    private String avatarPath;
    private String password;
    private String login;

    private UserRole role = UserRole.TEACHER;

    public Teacher(Groups groups, String firstname, String surname, String middlename, String number, String avatarPath, String login, String password) {
        this.groups = groups;
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
        this.number = number;
        this.avatarPath = avatarPath;
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

    public String getAvatarPath() { return avatarPath; }
    public void setAvatarPath(String avatarPath) { this.avatarPath = avatarPath; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
