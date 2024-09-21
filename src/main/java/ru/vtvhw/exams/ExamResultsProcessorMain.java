package ru.vtvhw.exams;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vtvhw.athletics.AthleticsMain;
import ru.vtvhw.exams.config.ExamResultsProcessorConfig;
import ru.vtvhw.exams.service.ExamAnswersLoader;
import ru.vtvhw.exams.service.ExamResultsProcessor;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExamResultsProcessorMain {
    public static void main(String[] args) throws URISyntaxException {
        var context = new AnnotationConfigApplicationContext(ExamResultsProcessorConfig.class);

        var examAnswersLoader = context.getBean(ExamAnswersLoader.class);
        var validResults = examAnswersLoader.loadAnswers(getPath("./exams/validAnswers.txt"));
        var studentResults = examAnswersLoader.loadAnswers(getPath("./exams/studentAnswers.txt"));

        System.out.println("Valid answers: ");
        validResults.forEach(System.out::println);
        System.out.println("\nStudent answers: ");
        studentResults.forEach(System.out::println);

        var examResultsProcessor = context.getBean(ExamResultsProcessor.class);
        System.out.printf("%nTotal points: %s%n", examResultsProcessor.calculateTotalPoints(validResults, studentResults));
    }

    private static Path getPath(String fileName) throws URISyntaxException {
        var file = AthleticsMain.class.getClassLoader().getResource(fileName);
        return Paths.get(file.toURI());
    }
}
