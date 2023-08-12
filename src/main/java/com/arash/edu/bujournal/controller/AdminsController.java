package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Admin;
import com.arash.edu.bujournal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class AdminsController {

    private final AdminService adminService;

    @GetMapping("/admins/{adminId}")
    public String showAdminPage(Model model, @PathVariable UUID adminId) {
        Admin admin = adminService.findById(adminId);
        model.addAttribute("admin", admin);
        return "redirect:/teachers";
    }
}
