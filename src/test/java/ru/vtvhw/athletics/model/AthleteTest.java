package ru.vtvhw.athletics.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AthleteTest {

    @Test
    void testEqualsByName() {
        Athlete athlete1 = new Athlete("Test", Gender.MALE);
        Athlete athlete2 = new Athlete("Test", Gender.MALE);
        Athlete athlete3 = new Athlete("Test", Gender.FEMALE);
        assertEquals(athlete1, athlete2);
        assertEquals(athlete1, athlete3);
    }

    @Test
    void testNotEqualsByName() {
        Athlete athlete1 = new Athlete("Test", Gender.MALE);
        Athlete athlete2 = new Athlete("Test2", Gender.MALE);
        assertNotEquals(athlete1, athlete2);
    }

    @Test
    void testNotEqualsByClass() {
        Athlete athlete = new Athlete("Test", Gender.MALE);
        assertNotEquals(athlete, "");
    }
}