package ru.vtvhw.exams.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.vtvhw.exams.service.PointsResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@ComponentScan("ru.vtvhw.exams")
@PropertySource("classpath:/exams/examMarks.properties")
public class ExamResultsProcessorConfig {
    @Value("#{'${firstGroup.questions}'.split(',')}")
    private Set<Integer> firstGroupQuestions;

    @Value("${firstGroup.points}")
    private int firstGroupPoints;

    @Value("#{'${secondGroup.questions}'.split(',')}")
    private Set<Integer> secondGroupQuestions;

    @Value("${secondGroup.points}")
    private int secondGroupPoints;

    @Value("#{'${thirdGroup.questions}'.split(',')}")
    private Set<Integer> thirdGroupQuestions;

    @Value("${thirdGroup.points}")
    private int thirdGroupPoints;

    @Bean
    public PointsResolver pointsResolver() {
        Map<Integer, Integer> pointForQuestion = new HashMap<>();

        firstGroupQuestions.forEach(question -> pointForQuestion.putIfAbsent(question, firstGroupPoints));
        secondGroupQuestions.forEach(question -> pointForQuestion.putIfAbsent(question, secondGroupPoints));
        thirdGroupQuestions.forEach(question -> pointForQuestion.putIfAbsent(question, thirdGroupPoints));

        return new PointsResolver(pointForQuestion);
    }
}
