package com.rocketbuilder.schoolbase.controllers;

import com.google.gson.Gson;
import com.rocketbuilder.schoolbase.enums.UserRole;
import com.rocketbuilder.schoolbase.models.Groups;
import com.rocketbuilder.schoolbase.models.Teacher;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import com.rocketbuilder.schoolbase.repos.TeacherRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupRepos groupRepos;
    @Autowired
    TeacherRepos teacherRepos;

    @GetMapping("/groups")
    @ResponseBody
    public String groups() {

        List<Groups> groups = groupRepos.findAll();
        return new Gson().toJson(groups);
    }

    @GetMapping("/group/{id}")
    public String groupSingle(@PathVariable("id") long id, Model model, HttpSession session) {
        Object sessionRole = session.getAttribute("role");
        if(sessionRole == null) {
            return "no-permisson";
        }
        UserRole role = UserRole.valueOf(sessionRole.toString());

        Groups group = groupRepos.findById(id);
        switch (role) {
            case TEACHER:
                long teacherId = Long.parseLong(session.getAttribute("userId").toString());
                Teacher teacher = teacherRepos.findById(teacherId);

                if(group.getTeacher().equals(teacher)) {
                    model.addAttribute("group", group);
                    return "group-single";
                } else
                    return "no-permisson";
            case ADMIN:
            case DIRECTOR:

                model.addAttribute("group", group);
                return "group-single";
            default:
                return "no-permisson";
        }
    }

}
