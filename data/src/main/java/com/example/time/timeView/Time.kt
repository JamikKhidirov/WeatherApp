package com.example.time.timeView

import com.example.time.dateTime.DataTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun getSamplemple(): Map<LocalDate, List<DataTime>>{

    val today = LocalDate.now()
    val dates: List<LocalDate> = listOf(
        today,
        today,
        today.minusDays(1),
        today.minusDays(2),
        today.minusDays(3),
    )
    val messagesWithIds = dates.mapIndexed { index, date ->
        DataTime(
            id = index + 1,
            date = dates[index]
        )
    }

    return messagesWithIds.groupBy { it.date }
}


//Форматирование даты для заголовка
fun formatDateHeader(date: LocalDate): String {
    val today = LocalDate.now()
    val yeaterDay = today.minusDays(1)

    return when {
        date == today -> "Сегодня"
        date == today.minusDays(1) -> "Вчера"
        else -> {
            val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
            date.format(formatter)
        }
    }
}
