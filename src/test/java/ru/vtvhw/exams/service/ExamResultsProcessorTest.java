package ru.vtvhw.exams.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vtvhw.exams.model.ExamAnswer;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExamResultsProcessorTest {
    private static final Map<Integer, Integer> POINTS_FOR_QUESTIONS = Map.of(1, 1, 2, 2, 3, 3);
    private static final PointsResolver POINTS_RESOLVER = new PointsResolver(POINTS_FOR_QUESTIONS);

    private static final Set<ExamAnswer> VALID_ANSWERS = Set.of(new ExamAnswer(1, 'A'),
            new ExamAnswer(2, 'B'),
            new ExamAnswer(3, 'C'));

    private static final Set<ExamAnswer> WRONG_ANSWERS = Set.of(new ExamAnswer(1, 'D'),
            new ExamAnswer(2, 'E'),
            new ExamAnswer(3, 'F'));

    private static final Set<ExamAnswer> PART_ANSWERS = Set.of(new ExamAnswer(1, 'A'),
            new ExamAnswer(2, 'E'),
            new ExamAnswer(3, 'C'));

    private static Stream<Arguments> calculateTotalPointsTestData() {
        return Stream.of(
                arguments(VALID_ANSWERS, VALID_ANSWERS, 6),
                arguments(VALID_ANSWERS, WRONG_ANSWERS, 0),
                arguments(VALID_ANSWERS, PART_ANSWERS, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateTotalPointsTestData")
    void testCalculateTotalPoints(Set<ExamAnswer> validAnswers, Set<ExamAnswer> studentAnswers, int expectedPoints) {
        var examResultsProcessor = new ExamResultsProcessor(POINTS_RESOLVER);
        var points = examResultsProcessor.calculateTotalPoints(validAnswers, studentAnswers);
        assertEquals(expectedPoints, points);
    }
}