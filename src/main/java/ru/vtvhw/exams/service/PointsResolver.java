package ru.vtvhw.exams.service;

import java.util.Map;

public class PointsResolver {
    private Map<Integer, Integer> pointForQuestions;

    public PointsResolver(Map<Integer, Integer> pointForQuestions) {
        this.pointForQuestions = pointForQuestions;
    }

    public int getPointsForQuestionNumber(int questionNumber) {
        return pointForQuestions.getOrDefault(questionNumber, 0);
    }
}