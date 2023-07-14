package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Source;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SourceRepository extends MongoRepository<Source, UUID> {

    List<Source> findAllByLessonId(UUID lessonId);
}
