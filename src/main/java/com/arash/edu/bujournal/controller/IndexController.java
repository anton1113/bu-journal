package com.arash.edu.bujournal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("appVersion", appVersion);
        return "index";
    }
}
