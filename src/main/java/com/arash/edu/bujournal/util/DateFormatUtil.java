package com.arash.edu.bujournal.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateFormatUtil {

    private static final DateTimeFormatter DD_MM = DateTimeFormatter.ofPattern("dd/MM");
    private static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String ddMM(LocalDate localDate) {
        return DD_MM.format(localDate);
    }

    public static String ddMMyyyy(LocalDate localDate) {
        return DD_MM_YYYY.format(localDate);
    }
}
