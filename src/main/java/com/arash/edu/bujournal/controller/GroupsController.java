package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GroupsController {

    private final GroupService groupService;

    @GetMapping("/groups")
    public String showGroups(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }
}
