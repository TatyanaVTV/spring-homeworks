package ru.vtvhw.athletics.service;

import org.springframework.stereotype.Service;
import ru.vtvhw.athletics.model.Athlete;
import ru.vtvhw.athletics.model.Distance;
import ru.vtvhw.athletics.model.Gender;
import ru.vtvhw.athletics.model.Result;

import java.time.Duration;

@Service
public class ResultParser {
    private static final String SEPARATOR = ";";

    public Result parseResult(String lineFromCsvFile) {
        var resultParts = lineFromCsvFile.split(SEPARATOR);

        var athleteName = resultParts[0];
        var athleteGender = Gender.of(resultParts[1].charAt(0));
        var distance = Distance.of(resultParts[2]);
        var time = parseDurationFromString(resultParts[3]);

        var athlete = new Athlete(athleteName, athleteGender);
        return new Result(athlete, distance, time);
    }

    public static Duration parseDurationFromString(String time) {
        var timeParts = time.split(":");

        var seconds = 0;
        var multiplier = 1;

        for (int i = timeParts.length - 1; i >= 0; i--) {
            seconds += Integer.parseInt(timeParts[i]) * multiplier;
            multiplier *= 60;
        }

        return Duration.ofSeconds(seconds);
    }
}
