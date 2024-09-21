package ru.vtvhw.athletics.model;

public enum Gender {
    MALE, FEMALE;

    public static Gender of(char symbol) {
        switch (symbol) {
            case 'М', 'M' -> {
                return MALE;
            }
            case 'Ж', 'F' -> {
                return FEMALE;
            }
            default -> throw new IllegalArgumentException("Gender could be only 'M' or 'F'! Given: " + symbol);
        }
    }
}
