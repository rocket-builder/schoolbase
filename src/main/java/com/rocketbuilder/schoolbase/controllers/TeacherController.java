package com.rocketbuilder.schoolbase.controllers;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rocketbuilder.schoolbase.StudentSerializer;
import com.rocketbuilder.schoolbase.TeacherSerializer;
import com.rocketbuilder.schoolbase.models.Groups;
import com.rocketbuilder.schoolbase.models.Response;
import com.rocketbuilder.schoolbase.models.Student;
import com.rocketbuilder.schoolbase.models.Teacher;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import com.rocketbuilder.schoolbase.repos.TeacherRepos;
import com.rocketbuilder.schoolbase.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Controller
public class TeacherController {

    @Autowired
    TeacherRepos teacherRepos;
    @Autowired
    UserRepos userRepos;
    @Autowired
    GroupRepos groupsRepos;

    @PostMapping("/teacher/add")
    @ResponseBody
    public String teacherAdd(
            @RequestParam String firstname,
            @RequestParam String surname,
            @RequestParam String middlename,
            @RequestParam String grouptitle,
            @RequestParam String number,
            @RequestParam String login,
            @RequestParam String password

    ) {
        boolean isExists = false;
        isExists = teacherRepos.existsByLogin(login);
        isExists = userRepos.existsByLogin(login);

        if(isExists) {
            return new Gson().toJson(new Response("Логин занят", true));
        } else {

            Groups group = groupsRepos.findDistinctByTitle(grouptitle);

            String passwordHash = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();

            group.setTeacher(new Teacher(group, firstname, surname, middlename, number, login, passwordHash));
            groupsRepos.save(group);

            return new Gson().toJson(new Response("Успешно добавлено", false));
        }
    }

    @GetMapping("/teacher/{id}/get")
    @ResponseBody
    public String teacherGet(@PathVariable long id) {
        Teacher teacher = teacherRepos.findById(id);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Teacher.class, new TeacherSerializer())
                .create();
        return gson.toJson(teacher);
    }

    @PostMapping("/teacher/{id}/save")
    @ResponseBody
    public String teacherEdit(
            @PathVariable long id,
            @RequestParam String firstname,
            @RequestParam String surname,
            @RequestParam String middlename,
            @RequestParam String grouptitle,
            @RequestParam String number
            ) {
        Teacher teacher = teacherRepos.findById(id);
        teacher.setFirstname(firstname);
        teacher.setSurname(surname);
        teacher.setMiddlename(middlename);

        Groups group = groupsRepos.findDistinctByTitle(grouptitle);
        teacher.setGroups(group);
        teacher.setNumber(number);
        teacherRepos.save(teacher);

        return "Успешно обновлено";
    }

    @PostMapping("/teacher/delete")
    @ResponseBody
    public String teacherDelete( @RequestParam long id ) {
        Teacher teacher = teacherRepos.findById(id);
        Groups group = groupsRepos.findById(teacher.getGroups().getId());

        group.setTeacher(null);
        groupsRepos.save(group);

        teacher.setGroups(null);
        teacherRepos.save(teacher);
        teacherRepos.deleteById(id);
        return "Удалено";
    }
}
