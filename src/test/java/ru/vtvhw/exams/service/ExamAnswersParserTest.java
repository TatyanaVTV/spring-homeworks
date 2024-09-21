package ru.vtvhw.exams.service;

import org.junit.jupiter.api.Test;
import ru.vtvhw.exams.model.ExamAnswer;

import static org.junit.jupiter.api.Assertions.*;

class ExamAnswersParserTest {
    @Test
    public void testParseResult() {
        var parser = new ExamAnswersParser();

        var parsedExamResult = parser.parseResult("14 - T");
        assertEquals(parsedExamResult, new ExamAnswer(14, 'T'));

        parsedExamResult = parser.parseResult("99 - v");
        assertEquals(parsedExamResult, new ExamAnswer(99, 'V'));
    }
}