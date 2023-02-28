package com.arash.edu.bujournal.controller.rest;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping({"", "/v1"})
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET, path = "/teachers/{id}")
    public Teacher getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/teachers")
    public Teacher postTeacher(@RequestBody Teacher teacher) {
        return teacherService.postTeacher(teacher);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/teachers/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
}
