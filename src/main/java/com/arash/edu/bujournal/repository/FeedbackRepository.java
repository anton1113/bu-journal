package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Feedback;
import com.arash.edu.bujournal.domain.enums.FeedbackState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, UUID> {

    List<Feedback> findAllByStateOrderByCreatedOnDesc(FeedbackState state);

    boolean existsByTextAndSessionId(String text, String sessionId);
    int countAllBySessionIdAndCreatedOnAfter(String sessionId, LocalDateTime createdOn);
}
