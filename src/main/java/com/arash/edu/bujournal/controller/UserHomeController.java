package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.util.BuSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserHomeController {

    @GetMapping(value = {"/me"})
    public String showIndexPage(RedirectAttributes redirectAttributes) {
        BuUser loggedInUser = BuSecurityUtil.getLoggedInUser();
        redirectAttributes.addFlashAttribute("redirectedFrom", "/me");
        switch (loggedInUser.getRole()) {
            case STUDENT: {
                return "redirect:/students/" + loggedInUser.getExternalId();
            }
            case TEACHER: {
                return "redirect:/teachers/" + loggedInUser.getExternalId();
            }
            case ADMIN: {
                return "redirect:/admins/" + loggedInUser.getExternalId();
            }
            default: throw new IllegalStateException("Unexpected logged in user role " + loggedInUser.getRole() + ", userId=" + loggedInUser.getId());
        }
    }
}
