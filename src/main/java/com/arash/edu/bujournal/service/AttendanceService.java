package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Attendance;
import com.arash.edu.bujournal.repository.AttendanceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance findOrCreateByStudentIdAndLessonId(@NonNull Long studentId, @NonNull Long lessonId) {
        log.debug("Find or create attendance by studentId [{}] and lessonId [{}]", studentId, lessonId);
        return attendanceRepository.findByStudentIdAndLessonId(studentId, lessonId)
                .orElseGet(() -> attendanceRepository.save(new Attendance(studentId, lessonId)));
    }
}
