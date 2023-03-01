package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        log.info("Find all students");
        return studentRepository.findAll();
    }

    public List<Student> findAllByGroupId(@NonNull Long groupId) {
        log.info("Find students by group id [{}]", groupId);
        return studentRepository.findAllByGroupId(groupId);
    }

    public Student addStudent(@NonNull Student student) {
        log.info("Adding student {}", student);
        return studentRepository.save(student);
    }

    public void deleteById(@NonNull Long id) {
        log.info("Deleting student by id {}", id);
        studentRepository.deleteById(id);
    }
}
