package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        log.info("Find all students");
        return studentRepository.findAll();
    }

    public Student findById(@NonNull UUID id) {
        log.info("Find student by id [{}]", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found by id " + id));
    }

    public List<Student> findAllByGroupId(@NonNull UUID groupId) {
        log.info("Find students by group id [{}]", groupId);
        return studentRepository.findAllByGroupId(groupId);
    }

    public Student addStudent(@NonNull Student student) {
        log.info("Adding student {}", student);
        if (student.getId() == null) {
            student.setId(UUID.randomUUID());
        }
        return studentRepository.save(student);
    }

    public Student editStudent(@NonNull UUID id, @NonNull Student student) {
        log.info("Editing student with {}, {}", id, student);
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student with id " + id + "not found, unable to edit");
        }
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteById(@NonNull UUID id) {
        log.info("Deleting student by id {}", id);
        studentRepository.deleteById(id);
    }
}
