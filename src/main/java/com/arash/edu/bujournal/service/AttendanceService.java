package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Attendance;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AttendanceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.arash.edu.bujournal.constants.AttendanceConstants.ABSENT_CODE;
import static org.apache.commons.lang3.math.NumberUtils.createNumber;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance findOrCreateByStudentIdAndLessonId(@NonNull UUID studentId, @NonNull UUID lessonId) {
        log.debug("Find or create attendance by studentId [{}] and lessonId [{}]", studentId, lessonId);
        return attendanceRepository.findByStudentIdAndLessonId(studentId, lessonId)
                .orElseGet(() -> attendanceRepository.save(new Attendance(UUID.randomUUID(), studentId, lessonId)));
    }

    public Attendance putMark(@NonNull UUID attendanceId, String mark) {
        log.info("Put mark [{}] for attendance [{}]", mark, attendanceId);
        Attendance attendance = attendanceRepository.findById(attendanceId)
                        .orElseThrow(() -> new NotFoundException("Attendance not found by id " + attendanceId));
        if (StringUtils.isEmpty(mark)) {
            return clear(attendance);
        }
        if (isCreatable(mark)) {
            return setMark(attendance, createNumber(mark).intValue());
        } else if (ABSENT_CODE.equalsIgnoreCase(mark)) {
            return setAbsent(attendance, true);
        } else {
            throw new IllegalArgumentException("Unable to parse mark " + mark + " for attendance " + attendanceId);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private Attendance setAbsent(Attendance attendance, boolean absent) {
        attendance.setIsAbsent(absent);
        attendance.setMark(null);
        return attendanceRepository.save(attendance);
    }

    private Attendance setMark(Attendance attendance, int mark) {
        attendance.setIsAbsent(false);
        attendance.setMark(mark);
        return attendanceRepository.save(attendance);
    }

    private Attendance clear(Attendance attendance) {
        attendance.setMark(null);
        attendance.setIsAbsent(null);
        return attendanceRepository.save(attendance);
    }
}
