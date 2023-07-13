package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, UUID> {

    Optional<Attendance> findByStudentIdAndLessonId(UUID studentId, UUID lessonId);
    void deleteAllByLessonId(UUID lessonId);
    void deleteAllByStudentId(UUID studentId);
}
