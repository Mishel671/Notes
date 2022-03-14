package com.notes.data.database

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {

    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun stringToLocalDateTime(
        value: String
    ): LocalDateTime {
        return LocalDateTime.parse(value, dateTimeFormatter)
    }

    @TypeConverter
    fun localDateTimeToString(
        localDateTime: LocalDateTime
    ): String {
        return dateTimeFormatter.format(localDateTime)
    }


}