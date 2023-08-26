package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentCredentialsFactory {

    String getUsername(Student student) {
        log.debug("Generating username for student {}", student);
        return String.format("%s_%s", student.getFirstName(), student.getLastName()).toLowerCase();
    }

    String getPassword(Student student) {
        log.debug("Generating password for student {}", student);
        return String.format("%s_%s", student.getFirstName(), student.getLastName()).toLowerCase();
    }
}
