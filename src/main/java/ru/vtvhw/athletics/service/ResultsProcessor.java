package ru.vtvhw.athletics;

import ru.vtvhw.athletics.model.Gender;

import java.util.List;

public interface ResultsProcessor {
    void loadResults();
    List<String> topRunners(int count, int distance, Gender gender);
}
