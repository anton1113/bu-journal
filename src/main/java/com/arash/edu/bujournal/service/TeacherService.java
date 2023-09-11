package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.TeacherRepository;
import com.arash.edu.bujournal.service.listener.TeacherEventListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherEventListener teacherEventListener;
    private final Collator uaCollator;

    public List<Teacher> findAll() {
        log.info("Find all teachers");
        return teacherRepository.findAll().stream()
                .sorted((t1, t2) -> uaCollator.compare(t1.getLastName(), t2.getLastName()))
                .collect(Collectors.toList());
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
        Teacher created = teacherRepository.save(teacher);
        teacherEventListener.onTeacherCreated(teacher);
        return created;
    }

    public Teacher editTeacher(@NonNull UUID id, @NonNull Teacher teacher) {
        log.info("Editing teacher with {}, {}", id, teacher);
        if (!teacherRepository.existsById(id)) {
            throw new NotFoundException("Teacher with id " + id + "not found, unable to edit");
        }
        teacher.setId(id);
        Teacher edited = teacherRepository.save(teacher);
        teacherEventListener.onTeacherChanged(edited);
        return edited;
    }

    public void deleteTeacher(@NonNull UUID id) {
        log.info("Delete teacher by id [{}]", id);
        teacherRepository.deleteById(id);
        teacherEventListener.onTeacherDeleted(id);
    }
}
