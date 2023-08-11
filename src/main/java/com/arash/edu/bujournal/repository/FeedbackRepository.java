package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, UUID> {

    boolean existsByTextAndSessionId(String text, String sessionId);
    int countAllBySessionIdAndCreatedOnAfter(String sessionId, LocalDateTime createdOn);
}
