package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SubjectsController {

    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String showStudents(Model model) {
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);
        return "subjects";
    }
}
