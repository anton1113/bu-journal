package com.arash.edu.bujournal.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum LessonType {

    LECTURE("Lecture", "Лекція"),
    SEMINAR("Seminar", "Семінар");

    @Getter
    private final String value;

    @Getter
    private final String uaValue;

    public static LessonType fromUaValue(final String uaValue) {
        for (LessonType suggestionType : LessonType.values()) {
            if (suggestionType.uaValue.equals(uaValue)) {
                return suggestionType;
            }
        }
        throw new IllegalArgumentException("Unexpected uaValue '" + uaValue + "'");
    }

    public static List<String> uaValues() {
        return Stream.of(values())
                .map(LessonType::getUaValue)
                .collect(Collectors.toList());
    }
}
