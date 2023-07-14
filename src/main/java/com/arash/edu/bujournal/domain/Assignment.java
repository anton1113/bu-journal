package com.arash.edu.bujournal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bu_assignment")
public class Assignment {

    @Id
    private UUID id;

    private UUID lessonId;

    private String name;

    private String comment;

    private String link;

    private UUID attachmentId;
}
