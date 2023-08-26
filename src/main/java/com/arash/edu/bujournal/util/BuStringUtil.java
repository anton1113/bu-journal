package com.arash.edu.bujournal.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BuStringUtil {

    private static final String EMPTY = "";

    public static String safelyGetFirstSymbol(String s) {
        if (s == null || s.isEmpty()) {
            return EMPTY;
        }
        return s.substring(0, 1);
    }
}
