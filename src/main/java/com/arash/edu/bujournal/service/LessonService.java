package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Lesson;
import com.arash.edu.bujournal.repository.LessonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> findAllBySubjectId(@NonNull UUID subjectId) {
        log.info("Find all lessons by subjectId [{}]", subjectId);
        return lessonRepository.findAllBySubjectId(subjectId);
    }

    public Lesson add(@NonNull Lesson lesson) {
        log.info("Add lesson {}", lesson);
        if (lesson.getId() == null) {
            lesson.setId(UUID.randomUUID());
        }
        return lessonRepository.save(lesson);
    }
}
