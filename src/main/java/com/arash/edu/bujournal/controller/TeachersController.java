package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.domain.enums.BuUserRole;
import com.arash.edu.bujournal.service.GroupService;
import com.arash.edu.bujournal.service.SubjectService;
import com.arash.edu.bujournal.service.TeacherService;
import com.arash.edu.bujournal.util.BuSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class TeachersController {

    private final TeacherService teacherService;
    private final GroupService groupService;
    private final SubjectService subjectService;

    @GetMapping("/teachers/{teacherId}")
    public String showTeacherPage(Model model, @PathVariable UUID teacherId) {
        Teacher teacher = teacherService.findById(teacherId);
        List<Group> groups = groupService.findAllByCuratorId(teacherId);
        List<Subject> subjects = subjectService.findAllByTeacherId(teacherId);
        String teacherGroupNames = groups.stream()
                .map(Group::getName)
                .collect(Collectors.joining(", "));
        model.addAttribute("teacher", teacher);
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teacherGroupNames", teacherGroupNames);
        return "teacher";
    }

    @GetMapping("/teachers")
    public String showTeachers(Model model) {
        populateAllTeachers(model);
        return "teachers";
    }

    @GetMapping("/teachers/my-groups")
    public String showTeacherGroups(Model model) {
        BuUser loggedInUser = BuSecurityUtil.getLoggedInUser();
        if (loggedInUser.getRole() != BuUserRole.TEACHER) {
            throw new IllegalStateException("Requested to show teacher groups by non-teacher user " + loggedInUser);
        }

        Teacher teacher = teacherService.findById(loggedInUser.getExternalId());
        List<Group> groups = groupService.findAllByCuratorId(teacher.getId());
        model.addAttribute("teacher", teacher);
        model.addAttribute("groups", groups);
        return "groups";
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
    public String deleteTeacher(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherService.findById(id);
        redirectAttributes.addFlashAttribute("teacherDeleteCandidate", teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}/delete/confirm")
    public String confirmDeleteTeacher(@PathVariable UUID id) {
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
