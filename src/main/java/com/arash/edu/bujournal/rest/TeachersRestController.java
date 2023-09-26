package com.arash.edu.bujournal.rest;

import com.arash.edu.bujournal.api.TeachersApi;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.dtos.TeacherDTO;
import com.arash.edu.bujournal.mapper.TeachersMapper;
import com.arash.edu.bujournal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TeachersRestController implements TeachersApi {

    private final TeacherService teacherService;
    private final TeachersMapper teachersMapper;

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherService.findAll();
        return teachersMapper.toDTOList(teachers);
    }

    @Override
    public TeacherDTO postTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teachersMapper.toModel(teacherDTO);
        Teacher newTeacher = teacherService.addTeacher(teacher);
        return teachersMapper.toDTO(newTeacher);
    }

    @Override
    public TeacherDTO deleteTeacher(UUID teacherId) {
        Teacher deletedTeacher = teacherService.deleteTeacher(teacherId);
        return teachersMapper.toDTO(deletedTeacher);
    }
}
