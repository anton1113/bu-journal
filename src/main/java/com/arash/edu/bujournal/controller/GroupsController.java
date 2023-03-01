package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.service.GroupService;
import com.arash.edu.bujournal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GroupsController {

    private final GroupService groupService;
    private final StudentService studentService;

    @GetMapping("/groups")
    public String showGroups(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/groups/{id}")
    public String showGroup(@PathVariable Long id, Model model) {
        Group group = groupService.findById(id);
        List<Student> students = studentService.findAllByGroupId(group.getId());
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        Student draftStudent = new Student();
        draftStudent.setGroupId(group.getId());
        model.addAttribute("draftStudent", draftStudent);
        return "group";
    }

    @GetMapping("/groups/{groupId}/students/{studentId}/delete")
    public String deleteStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        studentService.deleteById(studentId);
        return "redirect:/groups/" + groupId;
    }

    @PostMapping("/groups/{groupId}/students")
    public String addStudent(@PathVariable Long groupId, @ModelAttribute Student student) {
        student.setGroupId(groupId);
        studentService.addStudent(student);
        return "redirect:/groups/" + groupId;
    }
}
