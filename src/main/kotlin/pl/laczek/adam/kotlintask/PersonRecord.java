package pl.laczek.adam.kotlintask;

import java.time.LocalDate;
import java.util.Optional;

public record PersonRecord(String name, LocalDate born, Optional<String> hobby) {
    public String dayOfBirth() {
        return born.getDayOfWeek().toString();
    }

    public boolean wasBornInPreviousCentury() {
        return born.isBefore(LocalDate.of(2001, 1, 1));
    }

    public boolean hasHobby() {
        return hobby.isPresent();
    }
}
