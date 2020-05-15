package com.rocketbuilder.schoolbase.controllers;

import com.rocketbuilder.schoolbase.models.Groups;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    GroupRepos groupRepos;

    @GetMapping("/home")
    public String home(Model model){

        List<Groups> groups = groupRepos.findAll();
        model.addAttribute("groups", groups);
        return "home";
    }
}
