package com.arash.edu.bujournal.util;

import com.arash.edu.bujournal.domain.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataProvider {

    private static final int RANDOM_ALPHABETIC_LENGTH = 6;

    public static Student createStudent() {
        Student student = new Student();
        student.setFirstName(RandomStringUtils.randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        student.setLastName(RandomStringUtils.randomAlphabetic(RANDOM_ALPHABETIC_LENGTH));
        return student;
    }
}
