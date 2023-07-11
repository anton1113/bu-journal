package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.domain.Lesson;
import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.service.GroupService;
import com.arash.edu.bujournal.service.LessonService;
import com.arash.edu.bujournal.service.SubjectService;
import com.arash.edu.bujournal.service.TeacherService;
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

@RequiredArgsConstructor
@Controller
public class SubjectsController {

    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final LessonService lessonService;

    @GetMapping("/subjects")
    public String showSubjects(Model model) {
        List<Subject> subjects = subjectService.findAll();
        List<Teacher> teachers = teacherService.findAll();
        List<Group> groups = groupService.findAll();
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        model.addAttribute("addSubjectDraft", new Subject());
        return "subjects";
    }

    @GetMapping("/subjects/{id}")
    public String showSubject(@PathVariable UUID id, Model model) {
        Subject subject = subjectService.findById(id);
        List<Lesson> lessons = lessonService.findAllBySubjectId(id);
        model.addAttribute("subject", subject);
        model.addAttribute("lessons", lessons);
        model.addAttribute("draftLesson", new Lesson());
        return "subject";
    }

    @PostMapping("/subjects/{subjectId}/lessons")
    public String showSubject(@PathVariable UUID subjectId, @ModelAttribute Lesson lesson) {
        lesson.setSubjectId(subjectId);
        lessonService.add(lesson);
        return "redirect:/subjects/" + subjectId;
    }

    @PostMapping("/subjects")
    public String addSubject(@ModelAttribute Subject subject) {
        subjectService.add(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/{id}/delete")
    public String deleteSubject(@PathVariable UUID id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/{id}/draft")
    public String getSubjectDraft(@PathVariable UUID id, RedirectAttributes redirectAttrs) {
        Subject subject = subjectService.findById(id);
        redirectAttrs.addFlashAttribute("editSubjectDraft", subject);
        return "redirect:/subjects";
    }

    @PostMapping("/subjects/{id}")
    public String editSubject(@PathVariable UUID id, @ModelAttribute Subject subject) {
        subjectService.editSubject(id, subject);
        return "redirect:/subjects";
    }
}
