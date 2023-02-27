package com.arash.edu.bujournal.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(schema = "bu", name = "bu_lesson")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private LocalDate date;

    @Type(type = "uuid-char")
    private UUID disciplineId;

    @Type(type = "uuid-char")
    private UUID groupId;
}
