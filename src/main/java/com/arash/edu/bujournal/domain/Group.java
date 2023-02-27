package com.arash.edu.bujournal.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table(schema = "bu", name = "bu_group")
@Entity
public class Group {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    @Type(type = "uuid-char")
    private UUID curatorId;
}
