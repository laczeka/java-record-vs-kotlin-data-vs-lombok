package pl.laczek.adam.kotlintask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PersonLombokTest {
    @ParameterizedTest
    @CsvSource({
            "1990-05-15, TUESDAY, true",
            "2000-01-01, SATURDAY, true",
            "2005-03-10, THURSDAY, false"
    })
    void testDayOfBirth(String birthdate, String expectedDayOfWeek, boolean expectedIsPreviousCentury) {
        //when
        var person = personTemplate().born(LocalDate.parse(birthdate)).build();
        //then
        assertThat(person)
                .extracting(PersonLombok::dayOfBirth)
                .isEqualTo(expectedDayOfWeek);
        assertThat(person)
                .extracting(PersonLombok::wasBornInPreviousCentury)
                .isEqualTo(expectedIsPreviousCentury);
    }


    @ParameterizedTest
    @CsvSource({
            "Football, true",
            ", false"
    })
    void testHasHobby(String hobby, boolean expected) {
        var person = personTemplate().hobby(Optional.ofNullable(hobby)).build();
        assertThat(person)
                .extracting(PersonLombok::hasHobby)
                .isEqualTo(expected);
    }

    private static PersonLombok.PersonLombokBuilder personTemplate() {
        return PersonLombok.builder()
                .name("Demeter")
                .born(LocalDate.of(1982, 1, 1))
                .hobby(Optional.of("Greek mythology"));
    }

    @Test
    @DisplayName("This test show us how we encapsulate with Lombok!")
    void testEncapsulation() {
        var person = new PersonLombok("Demeter", LocalDate.of(1982, 1, 1), Optional.of("Greek mythology"));
        assertThat(person)
                .extracting(PersonLombok::getName)
                .isEqualTo("Demeter");

    }

    @Test
    void testMotherObjectBuilder() {
        var person = personTemplate().name("Persefona").build();
        assertThat(person)
                .extracting(PersonLombok::getName)
                .isEqualTo("Persefona");

    }

}