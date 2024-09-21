package ru.vtvhw.athletics.model;

import java.time.Duration;
import java.util.Comparator;

public record Result(Athlete athlete, Distance distance, Duration duration) {
    public boolean hasGender(Gender gender) {
        return athlete.gender() == gender;
    }

    public boolean hasDistance(Distance distance) {
        return this.distance == distance;
    }

    public static Comparator<Result> timeComparator() {
        return Comparator.comparingLong(o -> o.duration.getSeconds());
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s", athlete.name(), athlete.gender().name(), distance, duration);
    }
}