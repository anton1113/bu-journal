package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.MongoDbIntegrationTest;
import com.arash.edu.bujournal.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static com.arash.edu.bujournal.util.TestDataProvider.createStudent;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentServiceIntegrationTest extends MongoDbIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    void testFindAll() {
        // given
        UUID groupId = randomUUID();

        Student student1 = createStudent();
        student1.setLastName("Іванчен");
        student1.setGroupId(groupId);
        Student student2 = createStudent();
        student2.setLastName("Антонов");
        student2.setGroupId(groupId);
        Student student3 = createStudent();
        student3.setLastName("Єщенко");
        student3.setGroupId(groupId);

        studentRepository.saveAll(List.of(student1, student2, student3));

        // when
        List<Student> students = studentService.findAllByGroupId(groupId);

        // then
        assertNotNull(students);
        assertEquals(3, students.size());
        assertEquals(student2.getId(), students.get(0).getId());
        assertEquals(student3.getId(), students.get(1).getId());
        assertEquals(student1.getId(), students.get(2).getId());
    }
}
