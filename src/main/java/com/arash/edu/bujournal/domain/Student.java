package com.arash.edu.bujournal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bu_student")
public class Student {

    @Id
    private UUID id;

    private UUID groupId;

    private String lastName;

    private String firstName;

    private String patronymic;

    public String getFullName() {
        return String.join(" ",
                lastName == null ? StringUtils.EMPTY : lastName,
                firstName == null ? StringUtils.EMPTY : firstName,
                patronymic == null ? StringUtils.EMPTY : patronymic
        );
    }
}
