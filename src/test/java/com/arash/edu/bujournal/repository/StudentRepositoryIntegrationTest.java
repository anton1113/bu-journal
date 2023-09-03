package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.MongoDbIntegrationTest;
import com.arash.edu.bujournal.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testSorting() {
        // given
        final UUID groupId = randomUUID();

        Student student1 = createTestStudent(randomUUID(), "Іван", "Кожукар", groupId);
        Student student2 = createTestStudent(randomUUID(), "Іван", "Кіщук", groupId);
        studentRepository.saveAll(List.of(student1, student2));

        // when
        List<Student> students = studentRepository.findAllByGroupIdOrderByLastNameAsc(groupId);

        // then
        assertNotNull(students);
        assertEquals(2, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student2, students.get(1));
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

    private Student createTestStudent(UUID id, String firstName, String lastName, UUID groupId) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroupId(groupId);
        return student;
    }
}
