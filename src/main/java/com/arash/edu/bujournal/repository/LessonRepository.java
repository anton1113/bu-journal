package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository extends MongoRepository<Lesson, UUID> {

    List<Lesson> findAllBySubjectId(UUID subjectId);
    Optional<Lesson> findByIdAndSubjectId(UUID id, UUID subjectId);
    void deleteAllBySubjectId(UUID subjectId);
}
