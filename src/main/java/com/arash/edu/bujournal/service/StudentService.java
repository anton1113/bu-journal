package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.StudentRepository;
import com.arash.edu.bujournal.service.listener.StudentEventListener;
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
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentEventListener studentEventListener;
    private final Collator uaCollator;

    public List<Student> findAll() {
        log.info("Find all students");
        return studentRepository.findAll();
    }

    public Student findById(@NonNull UUID id) {
        log.info("Find student by id [{}]", id);
        return findStudent(id);
    }

    public List<Student> findAllByGroupId(@NonNull UUID groupId) {
        log.info("Find students by group id [{}]", groupId);
        return studentRepository.findAllByGroupId(groupId).stream()
                .sorted((s1, s2) -> uaCollator.compare(s1.getLastName(), s2.getLastName()))
                .collect(Collectors.toList());
    }

    public Student addStudent(@NonNull Student student) {
        log.info("Adding student {}", student);
        if (student.getId() == null) {
            student.setId(UUID.randomUUID());
        }
        Student created = studentRepository.save(student);
        studentEventListener.onStudentCreated(created);
        return created;
    }

    public Student editStudent(@NonNull UUID id, @NonNull Student student) {
        log.info("Editing student with {}, {}", id, student);
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student with id " + id + "not found, unable to edit");
        }
        student.setId(id);
        Student edited = studentRepository.save(student);
        studentEventListener.onStudentChanged(edited);
        return edited;
    }

    public Student deleteById(@NonNull UUID id) {
        log.info("Deleting student by id {}", id);
        Student student = findStudent(id);
        studentRepository.delete(student);
        studentEventListener.onStudentDeleted(id);
        return student;
    }

    private Student findStudent(@NonNull UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found by id " + id));
    }
}
