package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Lesson;
import com.arash.edu.bujournal.repository.LessonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> findAllBySubjectId(@NonNull Long subjectId) {
        log.info("Find all lessons by subjectId [{}]", subjectId);
        return lessonRepository.findAllBySubjectId(subjectId);
    }

    public Lesson add(@NonNull Lesson lesson) {
        log.info("Add lesson {}", lesson);
        return lessonRepository.save(lesson);
    }
}
