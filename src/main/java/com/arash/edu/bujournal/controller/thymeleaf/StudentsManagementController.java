package com.arash.edu.bujournal.controller.thymeleaf;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StudentsManagementController {

    private final StudentService studentService;

    @ModelAttribute("allStudents")
    private List<Student> populateStudents() {
        return studentService.findAll();
    }

    @RequestMapping({"/", "/studentsmng"})
    public String showStudents(final Student student){
        return "studentsmng";
    }
}
