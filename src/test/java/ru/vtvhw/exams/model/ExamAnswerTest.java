package ru.vtvhw.exams.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ExamAnswerTest {

    @Test
    void testEquals() {
        var examAnswer1 = new ExamAnswer(1, 'A');
        var examAnswer2 = new ExamAnswer(1, 'A');
        assertNotSame(examAnswer1, examAnswer2);
        assertEquals(examAnswer1, examAnswer2);
    }

    @Test
    void testHashCode() {
        var examAnswer = new ExamAnswer(1, 'A');
        assertEquals(examAnswer.hashCode(),
                Objects.hashCode(examAnswer.questionNumber()) + Objects.hashCode(examAnswer.answer()));
    }
}