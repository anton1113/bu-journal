package com.arash.edu.bujournal.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "bu_group")
public class Group {

    @Id
    private UUID id;

    private String name;

    private UUID curatorId;
}
