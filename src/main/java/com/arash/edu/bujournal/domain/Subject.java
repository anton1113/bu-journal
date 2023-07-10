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
@Document(collection = "bu_subject")
public class Subject {

    @Id
    private UUID id;

    private String name;

    private UUID teacherId;

    private UUID groupId;
}
