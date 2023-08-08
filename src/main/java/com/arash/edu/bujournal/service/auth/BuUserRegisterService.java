package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.domain.enums.BuUserRole;
import com.arash.edu.bujournal.repository.BuUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuUserRegisterService {

    private final BuUserRepository buUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BuUser registerStudent(Student student) {
        log.info("Registering student {}", student);
        String firstName = student.getFirstName();
        String lastName = student.getLastName();

        String username = toUsername(firstName, lastName);
        String password = toPassword(firstName, lastName);

        BuUser buUser = BuUser.builder()
                .id(UUID.randomUUID())
                .externalId(student.getId())
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(BuUserRole.STUDENT)
                .build();

        log.info("Created student user {}", buUser);
        return buUserRepository.save(buUser);
    }

    public BuUser registerTeacher(Teacher teacher) {
        log.info("Registering teacher {}", teacher);
        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        String username = toUsername(firstName, lastName);
        String password = toPassword(firstName, lastName);

        BuUser buUser = BuUser.builder()
                .id(UUID.randomUUID())
                .externalId(teacher.getId())
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(BuUserRole.TEACHER)
                .build();

        log.info("Created teacher user {}", buUser);
        return buUserRepository.save(buUser);
    }

    private String toUsername(String firstName, String lastName) {
        return String.format("%s_%s", firstName, lastName).toLowerCase();
    }

    private String toPassword(String firstName, String lastName) {
        return String.format("%s_%s", firstName, lastName).toLowerCase();
    }
}
