package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Attendance;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AttendanceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceService {

    private static final String ABSENT_CODE = "Н";

    private final AttendanceRepository attendanceRepository;

    public Attendance findOrCreateByStudentIdAndLessonId(@NonNull Long studentId, @NonNull Long lessonId) {
        log.debug("Find or create attendance by studentId [{}] and lessonId [{}]", studentId, lessonId);
        return attendanceRepository.findByStudentIdAndLessonId(studentId, lessonId)
                .orElseGet(() -> attendanceRepository.save(new Attendance(studentId, lessonId)));
    }

    public Attendance putMark(@NonNull Long attendanceId, String mark) {
        log.info("Put mark [{}] for attendance [{}]", mark, attendanceId);
        Attendance attendance = attendanceRepository.findById(attendanceId)
                        .orElseThrow(() -> new NotFoundException("Attendance not found by id " + attendanceId));
        if (StringUtils.isEmpty(mark)) {
            attendance.setMark(null);
            return attendanceRepository.save(attendance);
        }
        if (NumberUtils.isCreatable(mark)) {
            int intMark = NumberUtils.createNumber(mark).intValue();
            attendance.setMark(intMark);
            return attendanceRepository.save(attendance);
        } else if (ABSENT_CODE.equalsIgnoreCase(mark)) {
            attendance.setIsAbsent(true);
            return attendanceRepository.save(attendance);
        } else {
            throw new IllegalArgumentException("Unable to parse mark " + mark + " for attendance " + attendanceId);
        }
    }
}
