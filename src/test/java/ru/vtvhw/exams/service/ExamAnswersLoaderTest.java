package ru.vtvhw.exams.service;

import org.junit.jupiter.api.Test;
import ru.vtvhw.athletics.AthleticsMain;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ExamAnswersLoaderTest {

    @Test
    void testLoadAnswers() throws URISyntaxException {
        var examAnswersParser = new ExamAnswersParser();
        var examAnswersLoader = new ExamAnswersLoader(examAnswersParser);

        var testAnswersPath =  Paths.get(
                AthleticsMain.class.getClassLoader().getResource("./exams/testAnswers.txt").toURI()
        );

        var testResults = examAnswersLoader.loadAnswers(testAnswersPath);
        testResults.forEach(System.out::println);

        assertEquals(testResults.size(), 10);
    }
}