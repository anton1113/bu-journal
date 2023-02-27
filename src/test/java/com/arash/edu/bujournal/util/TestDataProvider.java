package com.arash.edu.bujournal.util;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.domain.Teacher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataProvider {

    private static final int RANDOM_ALPHABETIC_LENGTH = 6;

    public static Student createStudent() {
        Student student = new Student();
        student.setFirstName(randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        student.setLastName(randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        return student;
    }

    public static Teacher createTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName(randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        teacher.setLastName(randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        return teacher;
    }

    public static Group createGroup() {
        Group group = new Group();
        group.setName(randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        return group;
    }
}
