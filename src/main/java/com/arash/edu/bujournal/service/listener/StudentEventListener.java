package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.repository.AttendanceRepository;
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

    public void onStudentDeleted(@NonNull UUID studentId) {
        log.info("Received student_deleted event, studentId={}", studentId);

        attendanceRepository.deleteAllByStudentId(studentId);
    }
}
