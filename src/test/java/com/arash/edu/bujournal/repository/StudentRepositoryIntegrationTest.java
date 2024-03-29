package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.MongoDbIntegrationTest;
import com.arash.edu.bujournal.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentRepositoryIntegrationTest extends MongoDbIntegrationTest {

    @Test
    void testInsert() {
        // given
        final UUID studentId = randomUUID();

        // when
        studentRepository.save(createTestStudent(studentId));

        // then
        Optional<Student> dbStudent = studentRepository.findById(studentId);
        assertTrue(dbStudent.isPresent());
    }

    private Student createTestStudent(UUID id) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName("Foo");
        student.setLastName("Bar");
        student.setPatronymic("Bus");
        student.setGroupId(randomUUID());
        return student;
    }
}
