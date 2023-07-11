package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/teachers/{id}")
    public String editTeacher(@PathVariable UUID id, @ModelAttribute Teacher teacher) {
        teacherService.editTeacher(id, teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}/delete")
    public String deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}/draft")
    public String getTeacherDraft(@PathVariable UUID id, RedirectAttributes redirectAttrs) {
        Teacher teacher = teacherService.findById(id);
        redirectAttrs.addFlashAttribute("editTeacherDraft", teacher);
        return "redirect:/teachers";
    }

    private void populateAllTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("addTeacherDraft", new Teacher());
    }
}
