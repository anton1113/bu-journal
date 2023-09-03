package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.arash.edu.bujournal.util.BuStringUtil.toCleanWord;

@Slf4j
@Component
public class StudentCredentialsFactory {

    String getUsername(Student student) {
        log.debug("Generating username for student {}", student);
        String firstName = toCleanWord(student.getFirstName());
        String lastName = toCleanWord(student.getLastName());
        return String.format("%s_%s", firstName, lastName).toLowerCase();
    }

    String getPassword(Student student) {
        log.debug("Generating password for student {}", student);
        String firstName = toCleanWord(student.getFirstName());
        String lastName = toCleanWord(student.getLastName());
        return String.format("%s_%s", firstName, lastName).toLowerCase();
    }
}
