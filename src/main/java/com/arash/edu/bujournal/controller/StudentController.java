package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController("/v1/students")
public class StudentController {

    private final StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Student getStudent(@PathVariable UUID id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public Student postStudent(@PathVariable Student student) {
        return studentService.postStudent(student);
    }
}
