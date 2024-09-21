package ru.vtvhw.athletics.service;

import org.junit.jupiter.api.Test;
import ru.vtvhw.athletics.AthleticsMain;
import ru.vtvhw.athletics.model.Athlete;
import ru.vtvhw.athletics.model.Distance;
import ru.vtvhw.athletics.model.Gender;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvResultsProcessorTest {
//    private static final String FAKE_CSV = """
//            Вторая;F;10 km;01:08:38
//            Третья;F;10 km;01:12:88
//            Первая;F;10 km;01:07:57
//            Второй;M;10 km;00:55:37
//            Первый;M;10 km;00:54:09
//            Третий;M;10 km;00:56:12
//            Третья;F;5 km;00:37:28
//            Первая;F;5 km;00:35:47
//            Вторая;F;5 km;00:35:53
//            Второй;M;5 km;00:29:49
//            Третий;M;5 km;00:30:18
//            Первый;M;5 km;00:29:45
//            """.trim();

    @Test
    void testLoadResultsFromFile() throws URISyntaxException {
        var filePath = Paths.get(AthleticsMain.class.getClassLoader().getResource("AthleticResults.csv").toURI());
        var resultsParser = new ResultParser();
        var resultsProcessor = new CsvResultsProcessor(resultsParser);
        var loadedResults = resultsProcessor.loadResultsFromFile(filePath);
        loadedResults.forEach(System.out::println);
        assertEquals(loadedResults.size(), 12);
    }

    @Test
    void topRunners() throws URISyntaxException {
        var filePath = Paths.get(AthleticsMain.class.getClassLoader().getResource("AthleticResults.csv").toURI());
        var resultsParser = new ResultParser();
        var resultsProcessor = new CsvResultsProcessor(resultsParser);
        resultsProcessor.loadResultsFromFile(filePath);

        var topThreeResultsForMaleTenKm = resultsProcessor.topRunners(Gender.MALE, Distance.TEN_KM, 3);
        topThreeResultsForMaleTenKm.forEach(System.out::println);
        assertEquals(topThreeResultsForMaleTenKm.get(0).athlete(), new Athlete("Первый", Gender.MALE));
        assertEquals(topThreeResultsForMaleTenKm.get(1).athlete(), new Athlete("Второй", Gender.MALE));
        assertEquals(topThreeResultsForMaleTenKm.get(2).athlete(), new Athlete("Третий", Gender.MALE));

        var topThreeResultsForFemaleFiveKm = resultsProcessor.topRunners(Gender.FEMALE, Distance.FIVE_KM, 3);
        topThreeResultsForFemaleFiveKm.forEach(System.out::println);
        assertEquals(topThreeResultsForFemaleFiveKm.get(0).athlete(), new Athlete("Первая", Gender.FEMALE));
        assertEquals(topThreeResultsForFemaleFiveKm.get(1).athlete(), new Athlete("Вторая", Gender.FEMALE));
        assertEquals(topThreeResultsForFemaleFiveKm.get(2).athlete(), new Athlete("Третья", Gender.FEMALE));
    }
}