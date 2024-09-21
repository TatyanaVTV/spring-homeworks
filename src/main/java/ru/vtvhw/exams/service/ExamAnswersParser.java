package ru.vtvhw.exams.service;

import org.springframework.stereotype.Service;
import ru.vtvhw.exams.model.ExamAnswer;

@Service
public class ExamAnswersParser {
    public ExamAnswer parseResult(String rawResult) {
        var resultParts = rawResult.split(" - ");
        return new ExamAnswer(Integer.parseInt(resultParts[0]), resultParts[1].toUpperCase().charAt(0));
    }
}
