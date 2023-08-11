package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.util.BuSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String showIndexPage() {
        Optional<BuUser> loggedInUser = BuSecurityUtil.getLoggedInUserIfPresent();
        if (loggedInUser.isPresent()) {
            return "redirect:/me";
        } else {
            return "login";
        }
    }
}
