package ru.vtvhw.athletics.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GenderTest {
    private static Stream<Arguments> genderOfTestValues() {
        return Stream.of(
                arguments('M', Gender.MALE),
                arguments('М', Gender.MALE),
                arguments('F', Gender.FEMALE),
                arguments('Ж', Gender.FEMALE)
        );
    }

    @ParameterizedTest
    @MethodSource("genderOfTestValues")
    void testGenderOfValidValue(char symbol, Gender expectedGender) {
        assertEquals(Gender.of(symbol), expectedGender);
    }

    @Test
    void testGenderOfInValidValue() {
        assertThrows(IllegalArgumentException.class, () -> Gender.of('W'));
    }
}