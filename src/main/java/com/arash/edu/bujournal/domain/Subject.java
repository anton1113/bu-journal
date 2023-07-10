package com.arash.edu.bujournal.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "bu_subject")
public class Subject {

    @Id
    private UUID id;

    private String name;

    private UUID teacherId;

    private UUID groupId;
}
