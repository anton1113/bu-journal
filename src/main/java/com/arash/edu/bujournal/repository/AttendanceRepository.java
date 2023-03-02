package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByStudentIdAndLessonId(Long studentId, Long lessonId);
}
