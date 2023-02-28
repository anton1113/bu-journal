package com.arash.edu.bujournal.controller.rest;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping({"", "/v1"})
@RestController
public class StudentController {

    private final StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, path = "/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/students")
    public Student postStudent(@RequestBody Student student) {
        return studentService.postStudent(student);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
