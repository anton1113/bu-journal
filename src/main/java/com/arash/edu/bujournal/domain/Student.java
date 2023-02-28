package com.arash.edu.bujournal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(schema = "bu", name = "bu_student")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    private String patronymic;
}
