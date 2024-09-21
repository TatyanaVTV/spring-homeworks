package ru.vtvhw.athletics.service;

import ru.vtvhw.athletics.model.Distance;
import ru.vtvhw.athletics.model.Gender;
import ru.vtvhw.athletics.model.Result;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public interface ResultsProcessor {
    Set<Result> loadResultsFromFile(Path filePath);
    List<Result> topRunners(Gender gender, Distance distance, int limit);
}
