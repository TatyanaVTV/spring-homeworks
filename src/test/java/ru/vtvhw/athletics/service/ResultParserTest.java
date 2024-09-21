package ru.vtvhw.athletics.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vtvhw.athletics.model.Athlete;
import ru.vtvhw.athletics.model.Distance;
import ru.vtvhw.athletics.model.Gender;
import ru.vtvhw.athletics.model.Result;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ResultParserTest {

    @Test
    void testParseResult() {
        var lineFromCsv = "Athlete;M;10 km;01:00:39";
        var resultsParser = new ResultParser();

        var expectedResult = new Result(
                new Athlete("Athlete", Gender.MALE), Distance.TEN_KM, Duration.of(3639, ChronoUnit.SECONDS)
        );

        var resultOfParsing = resultsParser.parseResult(lineFromCsv);
        System.out.println(resultOfParsing);
        assertEquals(resultOfParsing, expectedResult);
    }

    private static Stream<Arguments> durationFromStringTestData() {
        return Stream.of(
                arguments("00:00:00", Duration.of(0, ChronoUnit.SECONDS)),
                arguments("00:00:01", Duration.of(1, ChronoUnit.SECONDS)),
                arguments("00:00:59", Duration.of(59, ChronoUnit.SECONDS)),
                arguments("00:01:00", Duration.of(60, ChronoUnit.SECONDS)),
                arguments("00:59:00", Duration.of(3540, ChronoUnit.SECONDS)),
                arguments("01:00:00", Duration.of(3600, ChronoUnit.SECONDS)),
                arguments("24:00:00", Duration.of(86400, ChronoUnit.SECONDS)),
                arguments("01:35:46", Duration.of(5746, ChronoUnit.SECONDS))
        );
    }

    @ParameterizedTest
    @MethodSource("durationFromStringTestData")
    void parseDurationFromString(String testedValue, Duration expectedResult) {
        var resultOfDurationParsing = ResultParser.parseDurationFromString(testedValue);
        assertEquals(resultOfDurationParsing, expectedResult);
    }
}