package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.arash.edu.bujournal.util.BuStringUtil.safelyGetFirstSymbol;
import static com.arash.edu.bujournal.util.BuStringUtil.toCleanWord;

@Slf4j
@Component
public class TeacherCredentialsFactory {

    String getUsername(Teacher teacher) {
        log.debug("Generating username for teacher {}", teacher);
        String lastName = toCleanWord(teacher.getLastName());
        String initials = getInitials(teacher);
        return String.format("%s_%s", lastName, initials).toLowerCase();
    }

    String getPassword(Teacher teacher) {
        log.debug("Generating password for teacher {}", teacher);
        String lastName = toCleanWord(teacher.getLastName());
        String initials = getInitials(teacher);
        return String.format("%s_%s", lastName, initials).toLowerCase();
    }

    private String getInitials(Teacher teacher) {
        String firstName = toCleanWord(teacher.getFirstName());
        String patronymic = toCleanWord(teacher.getPatronymic());
        return String.format("%s%s", safelyGetFirstSymbol(firstName), safelyGetFirstSymbol(patronymic));
    }
}
