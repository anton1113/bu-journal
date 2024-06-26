package com.arash.edu.bujournal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bu_student")
public class Student implements Serializable {

    @Id
    private UUID id;

    private UUID groupId;

    private String lastName;

    private String firstName;

    private String patronymic;

    public Student(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return String.join(" ",
                lastName == null ? StringUtils.EMPTY : lastName,
                firstName == null ? StringUtils.EMPTY : firstName,
                patronymic == null ? StringUtils.EMPTY : patronymic
        );
    }
}
