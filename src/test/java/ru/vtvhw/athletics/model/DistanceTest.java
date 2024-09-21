package ru.vtvhw.athletics.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    @Test
    void testToString() {
        for (Distance distance : Distance.values()) {
            assertEquals(distance.toString(), distance.getValue());
        }
    }

    @Test
    void testDistanceOfValidValue() {
        for (Distance distance : Distance.values()) {
            var testDistance = Distance.of(distance.getValue());
            assertEquals(testDistance, distance);
        }
    }
}