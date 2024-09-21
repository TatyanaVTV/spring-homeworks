package ru.vtvhw.athletics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtvhw.athletics.model.Distance;
import ru.vtvhw.athletics.model.Gender;
import ru.vtvhw.athletics.model.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CsvResultsProcessor implements ResultsProcessor {
    private Set<Result> loadedResults;

    @Autowired
    private ResultParser resultParser;

    public CsvResultsProcessor(ResultParser parser) {
        this.resultParser = parser;
    }

    @Override
    public Set<Result> loadResultsFromFile(Path filePath) {
        try(Stream<String> stream = Files.lines(filePath);) {
            loadedResults = stream.map(resultParser::parseResult)
                    .collect(Collectors.toSet());
            return loadedResults;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<Result> topRunners(Gender gender, Distance distance, int limit) {
        return loadedResults.stream()
                .filter(result -> result.hasDistance(distance) && result.hasGender(gender))
                .sorted(Result.timeComparator())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
