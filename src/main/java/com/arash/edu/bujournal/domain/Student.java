package com.arash.edu.bujournal.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Student {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;
}
