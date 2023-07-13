package dev.hayohtee.quicknote.data.repository

import dev.hayohtee.quicknote.data.local.dao.LocalNoteDao
import dev.hayohtee.quicknote.data.local.model.LocalNote
import dev.hayohtee.quicknote.domain.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val localNoteDao: LocalNoteDao) {
    suspend fun getAllNotes(): Flow<List<Note>> {
        return withContext(Dispatchers.IO) {
            localNoteDao.getAll().transform { localNotes ->
                emit(localNotes.map { localNote -> localNote.toNote() })
            }
        }
    }

    suspend fun getNote(id: Long): Flow<Note?> {
        return withContext(Dispatchers.IO) {
            localNoteDao.getById(id).transform { localNote ->
                emit( localNote?.toNote() )
            }
        }
    }

    suspend fun addNote(note: Note) {
        withContext(Dispatchers.IO) {
            localNoteDao.insert(note.toLocalNote())
        }
    }

    suspend fun updateNote(note: Note) {
        if (note.title.isEmpty() && note.content.isEmpty()) {
            throw IllegalArgumentException("Note must not be empty")
        }

        withContext(Dispatchers.IO) {
            localNoteDao.update(note.toLocalNote())
        }
    }

    suspend fun deleteNote(id: Long) {
        withContext(Dispatchers.IO) {
            localNoteDao.deleteById(id)
        }
    }

    suspend fun searchNotes(query: String): Flow<List<Note>> {
        return withContext(Dispatchers.IO) {
            localNoteDao.searchNotes(query).transform { localNotes ->
                emit(localNotes.map { localNote -> localNote.toNote() })
            }
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