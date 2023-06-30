package dev.hayohtee.quicknote.data.local.repository

import dev.hayohtee.quicknote.data.local.dao.LocalNoteDao
import dev.hayohtee.quicknote.data.local.model.LocalNote
import dev.hayohtee.quicknote.domain.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalNoteRepository(private val localNoteDao: LocalNoteDao) {
    suspend fun getAllNotes(): Flow<List<LocalNote>> {
        return withContext(Dispatchers.IO) {
            localNoteDao.getAll()
        }
    }

    suspend fun getNote(id: Long) : Note? {
        return withContext(Dispatchers.IO) {
            localNoteDao.getById(id)?.toNote()
        }
    }

    suspend fun addNote(note: Note) {
        withContext(Dispatchers.IO) {
            localNoteDao.insert(note.toLocalNote())
        }
    }

    suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            localNoteDao.update(note.toLocalNote())
        }
    }

    suspend fun deleteNote(id: Long) {
        withContext(Dispatchers.IO) {
            localNoteDao.deleteById(id)
        }
    }

}

private fun LocalNote.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        content = this.content,
        date = this.date
    )
}

private fun Note.toLocalNote(): LocalNote {
    return LocalNote(
        id = this.id,
        title = this.title,
        content = this.content,
        date = this.date
    )
}