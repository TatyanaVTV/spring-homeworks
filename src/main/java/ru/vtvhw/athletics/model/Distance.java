package ru.vtvhw.athletics.model;

import java.util.Objects;
import java.util.stream.Stream;

public enum Distance {
    FIVE_KM("5 km"), TEN_KM("10 km");

    private String value;

    Distance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Distance of(String valueToSearch) {
        return Stream.of(values())
                .filter(distance -> Objects.equals(distance.value, valueToSearch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown distance: " + valueToSearch));
    }

    @Override
    public String toString() {
        return getValue();
    }
}
