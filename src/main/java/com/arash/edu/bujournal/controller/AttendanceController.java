package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Attendance;
import com.arash.edu.bujournal.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PutMapping("/attendances/{attendanceId}")
    public Attendance putMark(@PathVariable UUID attendanceId, @RequestBody String mark) {
        return attendanceService.putMark(attendanceId, mark);
    }

    @DeleteMapping("/attendances/{attendanceId}")
    public Attendance deleteMark(@PathVariable UUID attendanceId) {
        return attendanceService.deleteMark(attendanceId);
    }
}
