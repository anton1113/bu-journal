package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class JournalsController {

    private final GroupService groupService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final AttendanceService attendanceService;

    @GetMapping("/journals")
    public String showJournals(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "journals";
    }

    @GetMapping("/journals/subjects/{subjectId}")
    public String showSubjectJournal(@PathVariable Long subjectId, Model model) {
        Subject subject = subjectService.findById(subjectId);
        Teacher teacher = teacherService.findById(subject.getTeacherId());
        Group group = groupService.findById(subject.getGroupId());
        List<Student> students = studentService.findAllByGroupId(group.getId());
        List<Lesson> lessons = lessonService.findAllBySubjectId(subject.getId());
        model.addAttribute("subject", subject);
        model.addAttribute("teacher", teacher);
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "journal";
    }
}
