package com.arash.edu.bujournal.init;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.repository.BuUserRepository;
import com.arash.edu.bujournal.repository.StudentRepository;
import com.arash.edu.bujournal.service.listener.StudentEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RestoreNonRegisteredStudents {

    private final StudentRepository studentRepository;
    private final BuUserRepository buUserRepository;

    private final StudentEventListener studentEventListener;

    @PostConstruct
    private void init() {
        List<Student> allStudents = studentRepository.findAll();
        allStudents.forEach(student -> {
            BuUser buUser = buUserRepository.findByExternalId(student.getId());
            if (buUser == null) {
                try {
                    studentEventListener.onStudentCreated(student);
                } catch (Exception e) {
                    // ignore
                }
            }
        });
    }
}
