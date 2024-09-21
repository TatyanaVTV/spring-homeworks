package ru.vtvhw.exams.model;

import java.util.Objects;

public record ExamAnswer(int questionNumber, char answer) {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExamAnswer
                && ((ExamAnswer) obj).questionNumber() == questionNumber()
                && ((ExamAnswer) obj).answer() == answer();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(questionNumber) + Objects.hashCode(answer);
    }

    @Override
    public String toString() {
        return questionNumber + " = " + answer;
    }
}