package ru.vtvhw.exams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtvhw.exams.model.ExamAnswer;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExamAnswersLoader {
    @Autowired
    private ExamAnswersParser examAnswersParser;

    public ExamAnswersLoader(ExamAnswersParser examAnswersParser) {
        this.examAnswersParser = examAnswersParser;
    }

    public Set<ExamAnswer> loadAnswers(Path filePath) {
        try (Stream<String> stream = Files.lines(filePath)) {
            return stream.map(examAnswersParser::parseResult).collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
