package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.service.GroupService;
import com.arash.edu.bujournal.service.StudentService;
import com.arash.edu.bujournal.service.TeacherService;
import com.arash.edu.bujournal.service.imports.StudentImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class GroupsController {

    private final GroupService groupService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final StudentImportService studentImportService;

    @GetMapping("/groups")
    public String showGroups(Model model) {
        List<Group> groups = groupService.findAll();
        List<Teacher> teachers = teacherService.findAll();
        groups.sort(Comparator.comparing(Group::getName));
        model.addAttribute("groups", groups);
        model.addAttribute("teachers", teachers);
        model.addAttribute("addGroupDraft", new Group());
        return "groups";
    }

    @PostMapping("/groups")
    public String addGroup(@ModelAttribute Group group) {
        groupService.add(group);
        return "redirect:/groups";
    }

    @PostMapping("/groups/{id}")
    public String editGroup(@PathVariable UUID id, @ModelAttribute Group group) {
        groupService.editGroup(id, group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}/delete")
    public String deleteGroup(@PathVariable UUID groupId, RedirectAttributes redirectAttributes) {
        Group group = groupService.findById(groupId);
        redirectAttributes.addFlashAttribute("groupDeleteCandidate", group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}/delete/confirm")
    public String deleteGroup(@PathVariable UUID groupId) {
        groupService.deleteById(groupId);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{id}")
    public String showGroup(@PathVariable UUID id, Model model) {
        Group group = groupService.findById(id);
        List<Student> students = studentService.findAllByGroupId(group.getId());
        Teacher curator = teacherService.findByNullableId(group.getCuratorId());
        model.addAttribute("group", group);
        model.addAttribute("curator", curator);
        model.addAttribute("students", students);
        Student addStudentDraft = new Student();
        addStudentDraft.setGroupId(group.getId());
        model.addAttribute("addStudentDraft", addStudentDraft);
        return "group";
    }

    @GetMapping("/groups/{id}/draft")
    public String getGroupDraft(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        Group group = groupService.findById(id);
        redirectAttributes.addFlashAttribute("editGroupDraft", group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}/students/{studentId}/delete")
    public String deleteStudentOfGroup(@PathVariable UUID groupId, @PathVariable UUID studentId, RedirectAttributes redirectAttributes) {
        Student student = studentService.findById(studentId);
        if (student.getGroupId() == null || !student.getGroupId().equals(groupId)) {
            throw new IllegalStateException("Student " + studentId + " is not in group " + groupId);
        }
        redirectAttributes.addFlashAttribute("studentDeleteCandidate", student);
        return "redirect:/groups/" + groupId;
    }


    @GetMapping("/groups/{groupId}/students/{studentId}/delete/confirm")
    public String confirmDeleteStudentOfGroup(@PathVariable UUID groupId, @PathVariable UUID studentId) {
        studentService.deleteById(studentId);
        return "redirect:/groups/" + groupId;
    }

    @GetMapping("/groups/{groupId}/students/{studentId}/draft")
    public String getStudentOfGroupDraft(@PathVariable UUID groupId, @PathVariable UUID studentId, RedirectAttributes redirectAttrs) {
        Student student = studentService.findById(studentId);
        if (student.getGroupId() == null || !student.getGroupId().equals(groupId)) {
            throw new IllegalStateException("Student " + studentId + " is not in group " + groupId);
        }
        redirectAttrs.addFlashAttribute("editStudentDraft", student);
        return "redirect:/groups/" + groupId;
    }

    @PostMapping("/groups/{groupId}/students/{studentId}")
    public String editStudentOfGroupDraft(@PathVariable UUID groupId, @PathVariable UUID studentId, @ModelAttribute Student student) {
        studentService.editStudent(studentId, student);
        return "redirect:/groups/" + groupId;
    }

    @PostMapping("/groups/{groupId}/students")
    public String addStudentToGroup(@PathVariable UUID groupId, @ModelAttribute Student student) {
        student.setGroupId(groupId);
        studentService.addStudent(student);
        return "redirect:/groups/" + groupId;
    }

    @PostMapping("/groups/{groupId}/students/import")
    public String importStudentsToGroup(@PathVariable UUID groupId, @RequestParam("rawCsv") String rawCsv, RedirectAttributes redirectAttributes) {
        try {
            studentImportService.importManyFromCsv(rawCsv, groupId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("importStudentsError", e.getMessage());
        }
        return "redirect:/groups/" + groupId;
    }
}
