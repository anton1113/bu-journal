package com.arash.edu.bujournal.mapper;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.dtos.StudentDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class StudentsMapper {

    public List<StudentDTO> toDTOList(@NonNull List<Student> students) {
        return students.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO toDTO(@NonNull Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setGroupId(student.getGroupId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setPatronymic(student.getPatronymic());
        return studentDTO;
    }

    public Student toModel(@NonNull StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setGroupId(studentDTO.getGroupId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setPatronymic(studentDTO.getPatronymic());
        return student;
    }
}
