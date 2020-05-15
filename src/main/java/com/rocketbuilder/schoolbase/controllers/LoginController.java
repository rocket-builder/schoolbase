package com.rocketbuilder.schoolbase.controllers;


import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.rocketbuilder.schoolbase.models.Response;
import com.rocketbuilder.schoolbase.models.User;
import com.rocketbuilder.schoolbase.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {

    @Autowired
    UserRepos userRepos;

    @GetMapping("/login")
    public String loginStart() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String login, @RequestParam String password, HttpSession session) {
        if(!userRepos.existsByLogin(login)) {
            return new Gson().toJson(new Response("Пользователь с таким логином не существует", true));
        }
        User user = userRepos.findByLogin(login);

        String passwordHash = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        if(!user.getPassword().equals(passwordHash)) {
            return new Gson().toJson(new Response("Неверный пароль", true));
        }

        session.setAttribute("login", login);
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole().toString());

        return new Gson().toJson(new Response("Success", true, user.getId()));
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}
