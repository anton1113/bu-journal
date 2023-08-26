package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.arash.edu.bujournal.util.BuStringUtil.safelyGetFirstSymbol;

@Slf4j
@Component
public class TeacherCredentialsFactory {

    String getUsername(Teacher teacher) {
        log.debug("Generating username for teacher {}", teacher);
        String initials = String.format("%s%s", safelyGetFirstSymbol(teacher.getFirstName()), safelyGetFirstSymbol(teacher.getPatronymic()));
        return String.format("%s_%s", teacher.getLastName(), initials).toLowerCase();
    }

    String getPassword(Teacher teacher) {
        log.debug("Generating password for teacher {}", teacher);
        String initials = String.format("%s%s", safelyGetFirstSymbol(teacher.getFirstName()), safelyGetFirstSymbol(teacher.getPatronymic()));
        return String.format("%s_%s", teacher.getLastName(), initials).toLowerCase();
    }
}
