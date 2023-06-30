package dev.hayohtee.quicknote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.hayohtee.quicknote.data.local.dao.LocalNoteDao
import dev.hayohtee.quicknote.data.local.model.LocalNote

@Database(entities = [LocalNote::class], version = 1, exportSchema = false)
@TypeConverters(LocalNoteTypeConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: LocalNoteDao
}