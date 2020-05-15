package com.rocketbuilder.schoolbase.controllers;

import com.rocketbuilder.schoolbase.models.Groups;
import com.rocketbuilder.schoolbase.repos.GroupRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    GroupRepos groupRepos;

    @GetMapping("/home")
    public String home(){

        List<Groups> groups = Arrays.asList(
                new Groups("1А"),
                new Groups("1Б"),
                new Groups("1В"),
                new Groups("1Г"),

                new Groups("2А"),
                new Groups("2Б"),
                new Groups("2В"),
                new Groups("2Г"),

                new Groups("3А"),
                new Groups("3Б"),
                new Groups("3В"),
                new Groups("3Г"),

                new Groups("4А"),
                new Groups("4Б"),
                new Groups("4В"),
                new Groups("4Г"),

                new Groups("5А"),
                new Groups("5Б"),
                new Groups("5В"),
                new Groups("5Г"),

                new Groups("6А"),
                new Groups("6Б"),
                new Groups("6В"),
                new Groups("6Г"),

                new Groups("7А"),
                new Groups("7Б"),
                new Groups("7В"),
                new Groups("7Г"),

                new Groups("8А"),
                new Groups("8Б"),
                new Groups("8В"),
                new Groups("8Г"),

                new Groups("9А"),
                new Groups("9Б"),
                new Groups("9В"),
                new Groups("9Г"),

                new Groups("10А"),
                new Groups("10Б"),
                new Groups("10В"),
                new Groups("10Г"),

                new Groups("11А"),
                new Groups("11Б"),
                new Groups("11В"),
                new Groups("11Г")
        );

        groupRepos.saveAll(groups);

        return "home";
    }
}
