package pl.laczek.adam.kotlintask;

import lombok.*;
import java.time.LocalDate;
import java.util.Optional;

@EqualsAndHashCode
@ToString
@Builder
public class PersonLombok {
    @Getter
    private String name;
    private LocalDate born;
    private Optional<String> hobby;

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
