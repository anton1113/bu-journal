package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.domain.enums.BuUserRole;
import com.arash.edu.bujournal.error.IllegalAccessException;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.BuUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuUserRegisterService {

    private final StudentCredentialsFactory studentCredentialsFactory;
    private final TeacherCredentialsFactory teacherCredentialsFactory;
    private final PasswordEncoder passwordEncoder;
    private final BuUserRepository buUserRepository;

    public BuUser registerStudent(Student student) {
        log.info("Registering student {}", student);

        String username = studentCredentialsFactory.getUsername(student);
        String password = studentCredentialsFactory.getPassword(student);

        BuUser buUser = BuUser.builder()
                .id(randomUUID())
                .externalId(student.getId())
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(BuUserRole.STUDENT)
                .build();

        log.info("Created student user {}", buUser);
        return buUserRepository.save(buUser);
    }

    public BuUser updateStudentCredentials(Student student) {
        log.info("Updating credentials for student {}", student);

        BuUser buUser = buUserRepository.findByExternalId(student.getId());
        if (buUser == null) {
            throw new NotFoundException("BuUser not found by student id " + student.getId());
        }

        if (buUser.getRole() != BuUserRole.STUDENT) {
            throw new IllegalAccessException("Unable to update student credentials for buUser " + buUser);
        }

        String username = studentCredentialsFactory.getUsername(student);
        String password = studentCredentialsFactory.getPassword(student);

        buUser.setUsername(username);
        buUser.setPassword(passwordEncoder.encode(password));

        log.info("Updated credentials for user {}", buUser);
        return buUserRepository.save(buUser);
    }

    public BuUser registerTeacher(Teacher teacher) {
        log.info("Registering teacher {}", teacher);

        String username = teacherCredentialsFactory.getUsername(teacher);
        String password = teacherCredentialsFactory.getPassword(teacher);

        BuUser buUser = BuUser.builder()
                .id(randomUUID())
                .externalId(teacher.getId())
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(BuUserRole.TEACHER)
                .build();

        log.info("Created teacher user {}", buUser);
        return buUserRepository.save(buUser);
    }

    public BuUser updateTeacherCredentials(Teacher teacher) {
        log.info("Updating credentials for teacher {}", teacher);

        BuUser buUser = buUserRepository.findByExternalId(teacher.getId());
        if (buUser == null) {
            throw new NotFoundException("BuUser not found by teacher id " + teacher.getId());
        }

        if (buUser.getRole() != BuUserRole.TEACHER) {
            throw new IllegalAccessException("Unable to update teacher credentials for buUser " + buUser);
        }

        String username = teacherCredentialsFactory.getUsername(teacher);
        String password = teacherCredentialsFactory.getPassword(teacher);

        buUser.setUsername(username);
        buUser.setPassword(passwordEncoder.encode(password));

        log.info("Updated credentials for user {}", buUser);
        return buUserRepository.save(buUser);
    }
}
