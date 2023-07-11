package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.TeacherRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        log.info("Find all teachers");
        return teacherRepository.findAll();
    }

    public Teacher findById(@NonNull UUID id) {
        log.info("Get teacher by id [{}]", id);
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found by id " + id));
    }

    public Teacher findByNullableId(UUID id) {
        log.info("Get teacher by nullable id [{}]", id);
        if (id == null) {
            return null;
        }
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found by id " + id));
    }

    public Teacher addTeacher(@NonNull Teacher teacher) {
        log.info("Adding teacher {}", teacher);
        if (teacher.getId() == null) {
            teacher.setId(UUID.randomUUID());
        }
        return teacherRepository.save(teacher);
    }

    public Teacher editTeacher(@NonNull UUID id, @NonNull Teacher teacher) {
        log.info("Editing teacher with {}, {}", id, teacher);
        if (!teacherRepository.existsById(id)) {
            throw new NotFoundException("Teacher with id " + id + "not found, unable to edit");
        }
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(@NonNull UUID id) {
        log.info("Delete teacher by id [{}]", id);
        teacherRepository.deleteById(id);
    }
}
