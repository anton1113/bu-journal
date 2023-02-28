package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TeachersController {

    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public String showStudents(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }
}
