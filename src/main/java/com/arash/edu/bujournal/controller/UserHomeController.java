package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.util.BuSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHomeController {

    @GetMapping(value = {"/me"})
    public String showIndexPage() {
        BuUser loggedInUser = BuSecurityUtil.getLoggedInUser();
        switch (loggedInUser.getRole()) {
            case STUDENT: {
                return "redirect:/students/" + loggedInUser.getExternalId();
            }
            case TEACHER: {
                return "redirect:/teachers/" + loggedInUser.getExternalId();
            }
            case DEAN: {
                return "redirect:/deans/" + loggedInUser.getExternalId();
            }
            case ADMIN: {
                return "redirect:/admins/" + loggedInUser.getExternalId();
            }
            default: throw new IllegalStateException("Unexpected logged in user role " + loggedInUser.getRole() + ", userId=" + loggedInUser.getId());
        }
    }
}
