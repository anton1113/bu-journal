package com.arash.edu.bujournal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(schema = "bu", name = "bu_lesson")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long subjectId;

    @Column(nullable = false)
    private Long groupId;
}
