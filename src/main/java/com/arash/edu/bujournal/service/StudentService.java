package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudent(@NonNull UUID id) {
        log.info("Get student by id [{}]", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found by id " + id));
    }

    public Student postStudent(@NonNull Student student) {
        log.info("Post student {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(@NonNull UUID id) {
        log.info("Delete student {}", id);
        studentRepository.deleteById(id);
    }
}
