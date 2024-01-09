package pl.laczek.adam.kotlintask;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Optional;


class PersonRecordTest {

    @ParameterizedTest
    @CsvSource({
            "1990-05-15, TUESDAY, true",
            "2000-01-01, SATURDAY, true",
            "2005-03-10, THURSDAY, false"
    })
    void testDayOfBirth(String birthdate, String expectedDayOfWeek, boolean expectedIsPreviousCentury) {
        //when
        var person = new PersonRecord("Adam", LocalDate.parse(birthdate), Optional.of("Kotlin"));
        //then
        assertThat(person)
                .extracting(PersonRecord::dayOfBirth)
                .isEqualTo(expectedDayOfWeek);
        assertThat(person)
                .extracting(PersonRecord::wasBornInPreviousCentury)
                .isEqualTo(expectedIsPreviousCentury);
    }


    @ParameterizedTest
    @CsvSource({
            "Football, true",
            ", false"
    })
    void testHasHobby(String hobby, boolean expected) {
        var person = new PersonRecord("Martin", LocalDate.of(1985, 2, 28), Optional.ofNullable(hobby));
        assertThat(person)
                .extracting(PersonRecord::hasHobby)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("This test show us how we break encapsulation with record!")
    void testEncapsulation() {
        var person = new PersonRecord("Demeter", LocalDate.of(1982, 1, 1), Optional.of("Greek mythology"));

        assertThat(person)
                .extracting(PersonRecord::born)
                .as("This could be confidential! But we show it")
                .isEqualTo(LocalDate.of(1982, 1, 1));
        assertThat(person)
                .extracting(PersonRecord::hobby)
                .as("We can see implementation detail and use it outside our package")
                .isEqualTo(Optional.of("Greek mythology"));
    }

    @Test
    void testMotherObjectBuilder() {
        PersonRecord person = personTemplate();
      //  person.withName("Persefona");
        assertThat(person)
                .extracting(PersonRecord::name)
                .isEqualTo("Persefona");

    }

    PersonRecord personTemplate() {
        return new PersonRecord("Demeter", LocalDate.of(1982, 1, 1), Optional.of("Greek mythology"));
    }
}