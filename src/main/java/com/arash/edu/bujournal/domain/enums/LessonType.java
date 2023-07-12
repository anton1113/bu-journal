package com.arash.edu.bujournal.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LessonType {

    LECTURE("Lecture", "Лекція"),
    SEMINAR("Seminar", "Семінар");

    @Getter(onMethod = @__(@JsonValue))
    private final String value;

    @Getter
    private final String uaValue;

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static LessonType fromValue(final String value) {
        for (LessonType suggestionType : LessonType.values()) {
            if (suggestionType.value.equals(value)) {
                return suggestionType;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
