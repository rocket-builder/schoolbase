package com.rocketbuilder.schoolbase.models;

import com.rocketbuilder.schoolbase.enums.GymGroup;
import com.rocketbuilder.schoolbase.enums.HealthGroup;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="student")
    private Set<Parent> parents;

    @ManyToOne
    @JoinColumn(name="groups_id", nullable=false)
    private Groups groups;

    private String firstname, surname, middlename;
    private String hobby;
    private int familyChildCount;
    private HealthGroup healthGroup;
    private GymGroup gymGroup;
    private String diseases;

    private Date birthdaty;
    private String avatarPath;

    public Student(Set<Parent> parents, Groups groups, String firstname, String surname, String middlename, String hobby, int familyChildCount, HealthGroup healthGroup, GymGroup gymGroup, String diseases, Date birthdaty, String avatarPath) {
        this.parents = parents;
        this.groups = groups;
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
        this.hobby = hobby;
        this.familyChildCount = familyChildCount;
        this.healthGroup = healthGroup;
        this.gymGroup = gymGroup;
        this.diseases = diseases;
        this.birthdaty = birthdaty;
        this.avatarPath = avatarPath;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Set<Parent> getParents() { return parents; }
    public void setParents(Set<Parent> parents) { this.parents = parents; }

    public Groups getGroups() { return groups; }
    public void setGroups(Groups groups) { this.groups = groups; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getMiddlename() { return middlename; }
    public void setMiddlename(String middlename) { this.middlename = middlename; }

    public String getHobby() { return hobby; }
    public void setHobby(String hobby) { this.hobby = hobby; }

    public int getFamilyChildCount() { return familyChildCount; }
    public void setFamilyChildCount(int familyChildCount) { this.familyChildCount = familyChildCount; }

    public HealthGroup getHealthGroup() { return healthGroup; }

    public void setHealthGroup(HealthGroup healthGroup) { this.healthGroup = healthGroup; }

    public GymGroup getGymGroup() { return gymGroup; }
    public void setGymGroup(GymGroup gymGroup) { this.gymGroup = gymGroup; }

    public String getDiseases() { return diseases; }
    public void setDiseases(String diseases) { this.diseases = diseases; }

    public Date getBirthdaty() { return birthdaty; }
    public void setBirthdaty(Date birthdaty) { this.birthdaty = birthdaty; }

    public String getAvatarPath() { return avatarPath; }
    public void setAvatarPath(String avatarPath) { this.avatarPath = avatarPath; }
}
