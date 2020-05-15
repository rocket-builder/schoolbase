package com.rocketbuilder.schoolbase.controllers;

import com.google.gson.Gson;
import com.rocketbuilder.schoolbase.models.Groups;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupRepos groupRepos;

    @GetMapping("/groups")
    @ResponseBody
    public String groups() {

        List<Groups> groups = groupRepos.findAll();
        return new Gson().toJson(groups);
    }


}
