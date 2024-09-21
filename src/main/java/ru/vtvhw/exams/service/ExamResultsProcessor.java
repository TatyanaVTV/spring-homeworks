package ru.vtvhw.exams.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.vtvhw.exams.model.ExamAnswer;

import java.util.Set;

@Service
@Scope("singleton")
public class ExamResultsProcessor {
    @Autowired
    private PointsResolver pointsResolver;

    public ExamResultsProcessor(PointsResolver pointsResolver) {
        this.pointsResolver = pointsResolver;
    }

    public int calculateTotalPoints(Set<ExamAnswer> validAnswers, Set<ExamAnswer> studentAnswers) {
        System.out.println("\nEarned points: ");
        return studentAnswers.stream()
                .filter(validAnswers::contains)
                .mapToInt(answer -> {
                    var points = pointsResolver.getPointsForQuestionNumber(answer.questionNumber());
                    System.out.printf("Valid answer: %s, points: %d%n", answer, points);
                    return points;
                })
                .sum();
    }
}
