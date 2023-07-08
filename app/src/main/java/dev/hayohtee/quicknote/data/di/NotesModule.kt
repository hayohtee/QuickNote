package dev.hayohtee.quicknote.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.hayohtee.quicknote.data.local.NoteDatabase
import dev.hayohtee.quicknote.data.local.dao.LocalNoteDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {
    @Singleton
    @Provides
    fun providesNoteDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            "notes_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesLocalNoteDao(noteDatabase: NoteDatabase): LocalNoteDao {
        return noteDatabase.noteDao
    }
}