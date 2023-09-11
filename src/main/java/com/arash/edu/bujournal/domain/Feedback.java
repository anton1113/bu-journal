package com.arash.edu.bujournal.domain;

import com.arash.edu.bujournal.domain.enums.FeedbackState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bu_feedback")
public class Feedback {

    @Id
    private UUID id;

    private String text;

    private String sessionId;

    private FeedbackState state;

    private LocalDateTime createdOn;

    private UUID createdBy;
}
