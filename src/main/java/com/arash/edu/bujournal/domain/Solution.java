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
@Document(collection = "bu_solution")
public class Solution {

    @Id
    private UUID id;

    private UUID assignmentId;

    private UUID studentId;

    private String comment;

    private String link;

    private UUID attachmentId;
}
