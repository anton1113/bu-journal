package com.arash.edu.bujournal.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table(schema = "bu", name = "bu_attendance")
@Entity
public class Attendance {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Type(type = "uuid-char")
    private UUID studentId;

    @Type(type = "uuid-char")
    private UUID lessonId;

    private Integer mark;

    private Boolean wasPresent;
}