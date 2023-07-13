package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Lesson;
import com.arash.edu.bujournal.repository.AttendanceRepository;
import com.arash.edu.bujournal.repository.LessonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class SubjectEventListener {

    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;

    public void onSubjectDeleted(@NonNull UUID subjectId) {
        log.info("Received subject_deleted event, subjectId={}", subjectId);

        List<Lesson> subjectLessons = lessonRepository.findAllBySubjectId(subjectId);
        if (!CollectionUtils.isEmpty(subjectLessons)) {
            log.info("Subject [{}] has lessons {}", subjectId, subjectLessons);
            subjectLessons.stream()
                    .map(Lesson::getId)
                    .forEach(attendanceRepository::deleteAllByLessonId);
            lessonRepository.deleteAll(subjectLessons);
        }
    }
}
