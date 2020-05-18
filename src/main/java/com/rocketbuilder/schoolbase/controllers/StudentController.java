package com.rocketbuilder.schoolbase.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rocketbuilder.schoolbase.ParentDeserializer;
import com.rocketbuilder.schoolbase.StudentDetailsSerializer;
import com.rocketbuilder.schoolbase.StudentSerializer;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepos studentRepos;
    @Autowired
    GroupRepos groupRepos;
    @Autowired
    UserRepos userRepos;

    private static String defFileDir = "/img/";
    private static String fileRootDir =  "./src/main/resources/static/img/";

    @GetMapping("/student/{id}")
    @ResponseBody
    public String studentById(@PathVariable("id") long id, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return new Gson().toJson(new Response("Нет полномочий", true));
        }

        Student student = studentRepos.findById(id);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Student.class, new StudentDetailsSerializer())
                .create();
        return gson.toJson(student);
    }

    @PostMapping("/student/{id}/avatar/add")
    @ResponseBody
    public String setAvatar(@PathVariable("id") long id, @RequestParam MultipartFile file) throws IOException {
        Student student = studentRepos.findById(id);

        String path = Upload.avatar(file);
        student.setAvatarPath(path);

        studentRepos.save(student);
        return new Gson().toJson(new Response("Успешно обновлено", false, path));
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
        student.setAvatarPath("/res/default-avatar.png");

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
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Student.class, new StudentSerializer())
                .create();
        return gson.toJson(student);
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

    @GetMapping("/student/search")
    @ResponseBody
    public Object[] eventSearch(@RequestParam String match) {

        List<Student> students = studentRepos.findBySurnameContainsIgnoreCase(match);
        ArrayList<SearchItem> results = new ArrayList<>();
        students.forEach(student -> results.add(
                new SearchItem(
                     student.getSurname() + " " + student.getFirstname(),
                     "/group/"+student.getGroups().getId(),
                     student.getAvatarPath()
                )
        ));
        //return new Gson().toJson(results);
        return results.toArray();
    }
}
