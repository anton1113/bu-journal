package com.arash.edu.bujournal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

    @Indexed(unique = true)
    private String text;

    private String sessionId;

    private LocalDateTime createdOn;

    private UUID createdBy;
}
