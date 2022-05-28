package com.example.fiqueok.ui.agendamento

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class ConversorDeData {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, org.threeten.bp.OffsetDateTime::from)
        }
    }

    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }
}