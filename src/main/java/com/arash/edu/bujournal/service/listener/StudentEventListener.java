package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.repository.AttendanceRepository;
import com.arash.edu.bujournal.service.auth.BuUserRegisterService;
import com.arash.edu.bujournal.service.auth.BuUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentEventListener {

    private final AttendanceRepository attendanceRepository;
    private final BuUserRegisterService buUserRegisterService;
    private final BuUserService buUserService;

    public void onStudentDeleted(@NonNull UUID studentId) {
        log.info("Received student_deleted event, studentId={}", studentId);
        attendanceRepository.deleteAllByStudentId(studentId);
        buUserService.deleteUserByExternalId(studentId);
    }

    public void onStudentCreated(@NonNull Student student) {
        log.info("Received student_created event, {}", student);
        buUserRegisterService.registerStudent(student);
    }

    public void onStudentChanged(@NonNull Student student) {
        log.info("Received student_changed event, {}", student);
        buUserRegisterService.updateStudentCredentials(student);
    }
}
