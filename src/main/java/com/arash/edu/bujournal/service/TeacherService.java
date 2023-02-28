package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.TeacherRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        log.info("Find all teachers");
        return teacherRepository.findAll();
    }

    public Teacher findById(@NonNull Long id) {
        log.info("Get teacher by id [{}]", id);
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found by id " + id));
    }

    public Teacher addTeacher(Teacher teacher) {
        log.info("Adding teacher {}", teacher);
        return teacherRepository.save(teacher);
    }
}
