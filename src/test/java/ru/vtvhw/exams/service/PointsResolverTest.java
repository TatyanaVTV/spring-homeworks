package ru.vtvhw.exams.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PointsResolverTest {
    @Test
    public void testGetPointForQuestionNumber() {
        var pointForQuestion = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        var pointResolver = new PointsResolver(pointForQuestion);

        pointForQuestion.entrySet().forEach(entry -> {
                    System.out.println(entry);
                    assertEquals(entry.getValue(), pointResolver.getPointsForQuestionNumber(entry.getKey()));
                }
        );
    }
}


//public PointsResolver(Map<Integer, Integer> pointForQuestions) {
//    this.pointForQuestions = pointForQuestions;
//}
//
//public int getPointsForQuestionNumber(int questionNumber) {
//    return pointForQuestions.getOrDefault(questionNumber, 0);
//}