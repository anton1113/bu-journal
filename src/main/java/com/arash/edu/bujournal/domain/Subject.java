package com.arash.edu.bujournal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(schema = "bu", name = "bu_subject")
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long teacherId;

    private Long groupId;
}
