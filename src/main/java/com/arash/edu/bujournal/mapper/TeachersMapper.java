package com.arash.edu.bujournal.mapper;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.dtos.TeacherDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TeachersMapper {

    public List<TeacherDTO> toDTOList(@NonNull List<Teacher> teachers) {
        return teachers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO toDTO(@NonNull Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setPatronymic(teacher.getPatronymic());
        return teacherDTO;
    }

    public Teacher toModel(@NonNull TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setPatronymic(teacherDTO.getPatronymic());
        return teacher;
    }
}
