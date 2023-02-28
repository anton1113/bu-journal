package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TeachersController {

    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public String showTeachers(Model model) {
        populateAllTeachers(model);
        return "teachers";
    }

    @PostMapping("/teachers")
    public String addTeacher(@ModelAttribute Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }

    private void populateAllTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("draftTeacher", new Teacher());
    }
}
