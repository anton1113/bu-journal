package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Lesson;
import com.arash.edu.bujournal.error.NotFoundException;
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

    public Lesson findById(@NonNull UUID id) {
        log.info("Find lesson by id [{}]", id);
        return lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson not found by id"));
    }

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

    public void deleteLessonFromSubject(@NonNull UUID subjectId, @NonNull UUID lessonId) {
        log.info("Delete lesson [{}] from subject [{}]", lessonId, subjectId);
        lessonRepository.findByIdAndSubjectId(lessonId, subjectId).ifPresentOrElse(
                lessonRepository::delete,
                () -> {
                    throw new NotFoundException("Lesson not found by id " + lessonId + " and subjectId " + subjectId);
                }
        );
    }

    public Lesson editLesson(@NonNull UUID id, @NonNull Lesson lesson) {
        log.info("Editing lesson with {}, {}", id, lesson);
        if (!lessonRepository.existsById(id)) {
            throw new NotFoundException("Lesson with id " + id + "not found, unable to edit");
        }
        lesson.setId(id);
        return lessonRepository.save(lesson);
    }
}
