package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.domain.enums.BuUserRole;
import com.arash.edu.bujournal.service.GroupService;
import com.arash.edu.bujournal.service.StudentService;
import com.arash.edu.bujournal.service.SubjectService;
import com.arash.edu.bujournal.service.TeacherService;
import com.arash.edu.bujournal.util.BuSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class StudentsController {

    private final StudentService studentService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/{studentId}")
    public String showStudentPage(Model model, @PathVariable UUID studentId) {
        Student student = studentService.findById(studentId);
        Group group = groupService.findById(student.getGroupId());
        Teacher curator = teacherService.findByNullableId(group.getCuratorId());
        List<Subject> subjects = subjectService.findAllByGroupId(group.getId());
        model.addAttribute("student", student);
        model.addAttribute("group", group);
        model.addAttribute("curator", curator);
        model.addAttribute("subjects", subjects);
        return "student";
    }

    @GetMapping("/students/my-group")
    public String showStudentsGroup() {
        BuUser loggedInUser = BuSecurityUtil.getLoggedInUser();
        if (loggedInUser.getRole() != BuUserRole.STUDENT) {
            throw new IllegalStateException("Requested to show student group by non-student user " + loggedInUser);
        }
        Student student = studentService.findById(loggedInUser.getExternalId());
        return "redirect:/groups/" + student.getGroupId();
    }
}
