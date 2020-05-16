package com.rocketbuilder.schoolbase.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rocketbuilder.schoolbase.ParentDeserializer;
import com.rocketbuilder.schoolbase.Upload;
import com.rocketbuilder.schoolbase.enums.GymGroup;
import com.rocketbuilder.schoolbase.enums.HealthGroup;
import com.rocketbuilder.schoolbase.enums.UserRole;
import com.rocketbuilder.schoolbase.models.*;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import com.rocketbuilder.schoolbase.repos.StudentRepos;
import com.rocketbuilder.schoolbase.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Controller
public class StudentController {

    @Autowired
    StudentRepos studentRepos;
    @Autowired
    GroupRepos groupRepos;
    @Autowired
    UserRepos userRepos;

    @GetMapping("/student/{id}")
    public String studentById(@PathVariable("id") long id, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return "no-permisson";
        }
        if(!studentRepos.existsById(id)) {
            return "not-founded";
        }

        Student student = studentRepos.findById(id);
        model.addAttribute("student", student);
        return "student-single";
    }

    @PostMapping("/student/{id}/avatar/add")
    @ResponseBody
    public String setAvatar(@PathVariable("id") long id, MultipartFile file) throws IOException {
        Student student = studentRepos.findById(id);
        student.setAvatarPath(Upload.avatar(file));

        studentRepos.save(student);
        return new Gson().toJson(new Response("Успешно обновлено", false));
    }

    @PostMapping("/student/{id}/parent/add")
    @ResponseBody
    public String setParents(@PathVariable("id") long id, @RequestParam String json) {
        Student student = studentRepos.findById(id);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Parent.class, new ParentDeserializer())
                .create();
        Parent parent = gson.fromJson(json, Parent.class);

        student.getParents().add(parent);
        studentRepos.save(student);
        return new Gson().toJson(new Response("Успешно обновлено", false));
    }

    @PostMapping("/student/add")
    @ResponseBody
    public String studentAdd(
            @RequestParam String firstname,
            @RequestParam String surname,
            @RequestParam String middlename,
            @RequestParam String grouptitle,
            @RequestParam String hobby,
            @RequestParam int familychildcount,
            @RequestParam String healthgroup,
            @RequestParam String gymgroup,
            @RequestParam String diseases,
            @RequestParam String birthday
            ) throws ParseException {

        Groups group = groupRepos.findDistinctByTitle(grouptitle);
        Student student = new Student(group, firstname, surname, middlename, hobby, familychildcount, HealthGroup.valueOf(healthgroup.toUpperCase()), GymGroup.valueOf(gymgroup.toUpperCase()), diseases, birthday);

        studentRepos.save(student);
        return new Gson().toJson(new Response("Успешно добавлено", false));
    }

    @GetMapping("/student/{id}/edit")
    @ResponseBody
    public String studentEdit(@PathVariable("id") long id, HttpSession session) {
        User user = userRepos.findByLogin(session.getAttribute("login").toString());

        if(!user.getRole().equals(UserRole.ADMIN)) {
            return "no-permisson";
        }

        Student student = studentRepos.findById(id);
        return new Gson().toJson(student);
    }

    @PostMapping("/student/{id}/save")
    @ResponseBody
    public String studentEditSave(
            @PathVariable("id") long id,
            @RequestParam String firstname,
            @RequestParam String surname,
            @RequestParam String middlename,
            @RequestParam String groupTitle,
            @RequestParam String hobby,
            @RequestParam int familyChildCount,
            @RequestParam String healthGroup,
            @RequestParam String gymGroup,
            @RequestParam String diseases,
            @RequestParam String birthday
    ) throws ParseException {

        Student student = studentRepos.findById(id);
        Groups group = groupRepos.findDistinctByTitle(groupTitle);
        student.setFirstname(firstname);
        student.setSurname(surname);
        student.setMiddlename(middlename);
        student.setGroups(group);
        student.setHobby(hobby);
        student.setFamilyChildCount(familyChildCount);
        student.setHealthGroup(HealthGroup.valueOf(healthGroup.toUpperCase()));
        student.setGymGroup(GymGroup.valueOf(gymGroup.toUpperCase()));
        student.setDiseases(diseases);
        student.setStringDate(birthday);
        studentRepos.save(student);

        return new Gson().toJson(new Response("Успешно обновлено", false));
    }

    @PostMapping("/student/delete")
    @ResponseBody
    public String studentDelete(@RequestParam long id) {
        studentRepos.deleteById(id);

        return new Gson().toJson(new Response("Успешно удалено", false));
    }

}
