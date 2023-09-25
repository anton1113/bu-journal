package com.arash.edu.bujournal.rest;

import com.arash.edu.bujournal.api.StudentsApi;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.dtos.StudentDTO;
import com.arash.edu.bujournal.mapper.StudentsMapper;
import com.arash.edu.bujournal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StudentsRestController implements StudentsApi {

    private final StudentService studentService;
    private final StudentsMapper studentsMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentService.findAll();
        return studentsMapper.toDTOList(students);
    }

    @Override
    public StudentDTO postStudent(StudentDTO studentDTO) {
        Student student = studentsMapper.toModel(studentDTO);
        Student newStudent = studentService.addStudent(student);
        return studentsMapper.toDTO(newStudent);
    }
}
