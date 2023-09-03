package com.arash.edu.bujournal.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuStringUtilTest {

    @Test
    void testToCleanWord() {
        final String s = "   Расщектаєв ;";
        final String expectedCleanWord = "Расщектаєв";

        String cleanWord = BuStringUtil.toCleanWord(s);

        assertEquals(expectedCleanWord, cleanWord);
    }

    @Test
    void testToCleanWordDoubleName() {
        final String s = " .,  Назар-Іван ;";
        final String expectedCleanWord = "Назар-Іван";

        String cleanWord = BuStringUtil.toCleanWord(s);

        assertEquals(expectedCleanWord, cleanWord);
    }
}
