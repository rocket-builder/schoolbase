package com.rocketbuilder.schoolbase.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rocketbuilder.schoolbase.StudentDetailsSerializer;
import com.rocketbuilder.schoolbase.enums.UserRole;
import com.rocketbuilder.schoolbase.models.Parent;
import com.rocketbuilder.schoolbase.models.Response;
import com.rocketbuilder.schoolbase.models.Student;
import com.rocketbuilder.schoolbase.models.User;
import com.rocketbuilder.schoolbase.repos.ParentRepos;
import com.rocketbuilder.schoolbase.repos.StudentRepos;
import com.rocketbuilder.schoolbase.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ParentController {

    @Autowired
    StudentRepos studentRepos;
    @Autowired
    ParentRepos parentRepos;
    @Autowired
    UserRepos userRepos;

    @PostMapping("/parent/{id}/add")
    @ResponseBody
    public String parentAdd(
        @PathVariable long id,
        @RequestParam String firstname,
        @RequestParam String surname,
        @RequestParam String middlename,
        @RequestParam String job,
        @RequestParam String number,

        @RequestParam String firstname2,
        @RequestParam String surname2,
        @RequestParam String middlename2,
        @RequestParam String job2,
        @RequestParam String number2
    ) {
        Student student = studentRepos.findById(id);

        student.getParents().add(new Parent(student, firstname, surname, middlename, job, number));
        student.getParents().add(new Parent(student, firstname2, surname2, middlename2, job2, number2));
        studentRepos.save(student);
        return "Успешно добавлено";
    }

    @GetMapping("/student/{id}/parent/get")
    @ResponseBody
    public String parentGet(@PathVariable("id") long id, HttpSession session) {
        if(session.getAttribute("login") == null)
            return new Gson().toJson(new Response("Нет полномочий для этого действия", true));

        Student student = studentRepos.findById(id);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Student.class, new StudentDetailsSerializer())
                .create();
        return gson.toJson(student);
    }

    @PostMapping("/parent/{id}/edit")
    @ResponseBody
    public String parentEdit(
            @PathVariable long id,
            @RequestParam String firstname,
            @RequestParam String surname,
            @RequestParam String middlename,
            @RequestParam String job,
            @RequestParam String number,

            @RequestParam String firstname2,
            @RequestParam String surname2,
            @RequestParam String middlename2,
            @RequestParam String job2,
            @RequestParam String number2
    ) {
        Student student = studentRepos.findById(id);

        student.getParents().get(0).setFirstname(firstname);
        student.getParents().get(0).setSurname(surname);
        student.getParents().get(0).setMiddlename(middlename);
        student.getParents().get(0).setJob(job);
        student.getParents().get(0).setNumber(number);

        student.getParents().get(1).setFirstname(firstname2);
        student.getParents().get(1).setSurname(surname2);
        student.getParents().get(1).setMiddlename(middlename2);
        student.getParents().get(1).setJob(job2);
        student.getParents().get(1).setNumber(number2);

        studentRepos.save(student);
        return "Успешно обновлено";
    }
}
