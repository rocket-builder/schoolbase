package com.rocketbuilder.schoolbase.controllers;

import com.rocketbuilder.schoolbase.enums.UserRole;
import com.rocketbuilder.schoolbase.models.Groups;
import com.rocketbuilder.schoolbase.models.User;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import com.rocketbuilder.schoolbase.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @Autowired
    GroupRepos groupRepos;
    @Autowired
    UserRepos userRepos;

    String[]colorsArray = {
            "red",
            "orange",
            "yellow",
            "olive",
            "green",
            "teal",
            "blue",
            "violet",
            "purple"
    };
    List<String> colors = Arrays.asList(colorsArray);

    private int getRandom() {
        int min = 0;
        int max = 8;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session){

        if(session.getAttribute("login") != null) {
            List<Groups> groups = groupRepos.findAll();

            groups.forEach(group -> group.setColor(colors.get(getRandom())));

            model.addAttribute("groups", groups);
            return "home";
        }
        return "login";
    }
}
