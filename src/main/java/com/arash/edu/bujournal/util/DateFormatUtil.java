package com.arash.edu.bujournal.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateFormatUtil {

    private static final DateTimeFormatter DD_MM = DateTimeFormatter.ofPattern("dd/MM");

    public static String ddMM(LocalDate localDate) {
        return DD_MM.format(localDate);
    }
}
