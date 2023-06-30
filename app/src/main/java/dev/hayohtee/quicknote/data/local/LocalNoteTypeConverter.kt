package dev.hayohtee.quicknote.data.local

import androidx.room.TypeConverter
import java.util.Date

class LocalNoteTypeConverter {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }
}