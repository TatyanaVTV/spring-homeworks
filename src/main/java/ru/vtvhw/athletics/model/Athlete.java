package ru.vtvhw.athletics.model;

public record Athlete(String name, Gender gender) {
    @Override
    public int hashCode() {
        return name.hashCode() + gender.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else return obj instanceof Athlete && name.equals(((Athlete) obj).name());
    }
}