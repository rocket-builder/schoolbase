package com.rocketbuilder.schoolbase.controllers;

import com.rocketbuilder.schoolbase.models.Parent;
import com.rocketbuilder.schoolbase.models.Student;
import com.rocketbuilder.schoolbase.repos.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParentController {

    @Autowired
    StudentRepos studentRepos;

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

        student.getParents().add(new Parent(firstname, surname, middlename, job, number));
        student.getParents().add(new Parent(firstname2, surname2, middlename2, job2, number2));
        studentRepos.save(student);
        return "Успешно добавлено";
    }
}
