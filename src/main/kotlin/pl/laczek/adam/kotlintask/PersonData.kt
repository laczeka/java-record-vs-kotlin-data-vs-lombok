package pl.laczek.adam.kotlintask

import java.time.LocalDate

data class PersonData(val name: String, private val born: LocalDate, private val hobby: String? = "Traveling") {
    fun dayOfBirth() = born.dayOfWeek.toString()
    fun wasBornInPreviousCentury() = born.isBefore(LocalDate.of(2001, 1, 1))
    fun hasHobby() = hobby != null
}