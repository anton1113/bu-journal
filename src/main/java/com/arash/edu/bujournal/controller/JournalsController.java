package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.error.IllegalAccessException;
import com.arash.edu.bujournal.service.*;
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
public class JournalsController {

    private final GroupService groupService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final LessonService lessonService;
    private final TeacherService teacherService;

    @GetMapping("/journals/subjects/{subjectId}")
    public String showSubjectJournal(@PathVariable UUID subjectId, Model model) {
        Subject subject = subjectService.findById(subjectId);
        Teacher teacher = teacherService.findByNullableId(subject.getTeacherId());
        Group group = groupService.findById(subject.getGroupId());
        List<Student> students = getJournalStudents(group.getId());
        List<Lesson> lessons = lessonService.findAllBySubjectId(subject.getId());
        model.addAttribute("subject", subject);
        model.addAttribute("teacher", teacher);
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "journal";
    }

    private List<Student> getJournalStudents(UUID groupId) {
        BuUser buUser = BuSecurityUtil.getLoggedInUser();
        switch (buUser.getRole()) {
            case STUDENT: {
                return List.of(studentService.findById(buUser.getExternalId()));
            }
            case ADMIN:
            case TEACHER: {
                return studentService.findAllByGroupId(groupId);
            }
            default: {
                throw new IllegalAccessException("Unable to prepare journal for role " + buUser.getRole());
            }
        }
    }
}
