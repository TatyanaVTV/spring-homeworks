package ru.vtvhw.athletics.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ResultTest {
    private static final Athlete FAKE_MALE_ATHLETE = new Athlete("Test", Gender.MALE);
    private static final Athlete FAKE_FEMALE_ATHLETE = new Athlete("Test", Gender.FEMALE);
    private static final Duration FAKE_TEN_MINUTES =  Duration.of(10, ChronoUnit.MINUTES);
    private static final Duration FAKE_TWENTY_MINUTES =  Duration.of(20, ChronoUnit.MINUTES);

    private static Stream<Arguments> testHasGenderParams() {
        return Stream.of(
                arguments(new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES), Gender.FEMALE, true),
                arguments(new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES), Gender.MALE, false),
                arguments(new Result(FAKE_MALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES), Gender.FEMALE, false),
                arguments(new Result(FAKE_MALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES), Gender.MALE, true)
        );
    }

    @ParameterizedTest
    @MethodSource("testHasGenderParams")
    void testHasGender(Result result, Gender checkedGender, boolean expectedResult) {
        assertEquals(result.hasGender(checkedGender), expectedResult);
    }

    private static Stream<Arguments> testHasDistanceParams() {
        return Stream.of(
                arguments(new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES), Distance.FIVE_KM, true),
                arguments(new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES), Distance.TEN_KM, false),
                arguments(new Result(FAKE_MALE_ATHLETE, Distance.TEN_KM, FAKE_TEN_MINUTES), Distance.FIVE_KM, false),
                arguments(new Result(FAKE_MALE_ATHLETE, Distance.TEN_KM, FAKE_TEN_MINUTES), Distance.TEN_KM, true)
        );
    }

    @ParameterizedTest
    @MethodSource("testHasDistanceParams")
    void hasDistance(Result result, Distance checkedDistance, boolean expectedResult) {
        assertEquals(result.hasDistance(checkedDistance), expectedResult);
    }

    @Test
    void testTimeComparator() {
        var resultTenMinutes = new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES);
        var anotherResultTenMinutes = new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TEN_MINUTES);
        var resultTwentyMinutes = new Result(FAKE_FEMALE_ATHLETE, Distance.FIVE_KM, FAKE_TWENTY_MINUTES);

        var resultOfComparison = Result.timeComparator().compare(resultTenMinutes, resultTwentyMinutes);
        assertTrue(resultOfComparison < 0);

        resultOfComparison = Result.timeComparator().compare(resultTenMinutes, anotherResultTenMinutes);
        assertEquals(0, resultOfComparison);

        resultOfComparison = Result.timeComparator().compare(resultTwentyMinutes, resultTenMinutes);
        assertTrue(resultOfComparison > 0);
    }
}