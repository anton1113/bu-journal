package com.arash.edu.bujournal.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table(schema = "bu", name = "bu_attendance")
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long lessonId;

    private Integer mark;

    private Boolean isAbsent;

    public Attendance(Long studentId, Long lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }
}
