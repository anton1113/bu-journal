package com.arash.edu.bujournal.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BuUserRole {

    STUDENT("Student", "Студент"),
    TEACHER("Teacher", "Викладач"),
    ADMIN("Admin", "Admin");

    @Getter
    private final String value;

    @Getter
    private final String uaValue;
}
