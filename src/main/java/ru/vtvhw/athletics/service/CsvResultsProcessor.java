package ru.vtvhw.athletics;

import ru.vtvhw.athletics.model.Gender;
import ru.vtvhw.athletics.model.Athlete;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvResultsProcessor implements ResultsProcessor {
    private Map<Athlete, String> loadedResults;

    @Override
    public void loadResults() {
        loadedResults = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("classpath:AthleticResults.csv"))) {
            String nextResult = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> topRunners(int count, int distance, Gender gender) {
        return List.of();
    }
}
