package pl.laczek.adam.kotlintask

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import io.kotest.matchers.shouldBe

class PersonDataTest {
    @ParameterizedTest
    @CsvSource(
            "1990-05-15, TUESDAY, true",
            "2000-01-01, SATURDAY, true",
            "2005-03-10, THURSDAY, false"
    )
    fun `test day of birth and was born in previous century`(birthdate: String, expectedDayOfWeek: String, expectedIsPreviousCentury: Boolean) {
        //when
        val person = personTemplate(born = LocalDate.parse(birthdate))
        //then
        person.dayOfBirth() shouldBe expectedDayOfWeek
        person.wasBornInPreviousCentury() shouldBe expectedIsPreviousCentury
    }

    @ParameterizedTest
    @CsvSource(
            "Football, true",
            ", false"
    )
    fun `has hobby`(givenHobby: String?, expected: Boolean) {
        val person = personTemplate(hobby = givenHobby)
        person.hasHobby() shouldBe expected
    }

    fun personTemplate(name: String = "Demeter",
                       born: LocalDate = LocalDate.of(1982, 1, 1),
                       hobby: String? = "Greek mythology"): PersonData {
        return PersonData(name = name, born = born, hobby = hobby)
    }

    @Test
    fun `test mother objets`() {
        val basketballKora = PersonData("Kora", LocalDate.of(1985, 2, 28), "Basketball")
        val travelingKora = PersonData("Martin", LocalDate.of(1985, 2, 28))
        val kora = PersonData(hobby = "eating pizza",
                name = "Kora",
                born = LocalDate.of(1985, 2, 28))
        val templateKora = personTemplate(name = "Kora")
        val persefonaFromKora = templateKora.copy(name = "Persefona")
        templateKora.name shouldBe "Kora"
        persefonaFromKora.name shouldBe "Persefona"
    }




}