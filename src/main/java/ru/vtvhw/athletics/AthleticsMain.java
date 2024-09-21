package ru.vtvhw.athletics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vtvhw.athletics.config.AthleticsConfig;
import ru.vtvhw.athletics.model.Distance;
import ru.vtvhw.athletics.model.Gender;
import ru.vtvhw.athletics.service.CsvResultsProcessor;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class AthleticsMain {
    public static void main(String[] args) throws URISyntaxException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AthleticsConfig.class);

        var filePath = Paths.get(AthleticsMain.class.getClassLoader().getResource("AthleticResults.csv").toURI());

        var csvResultProcessor = context.getBean(CsvResultsProcessor.class);
        csvResultProcessor.loadResultsFromFile(filePath);
        var top = csvResultProcessor.topRunners(Gender.MALE, Distance.TEN_KM, 3);
        top.forEach(System.out::println);

        context.close();
    }
}
