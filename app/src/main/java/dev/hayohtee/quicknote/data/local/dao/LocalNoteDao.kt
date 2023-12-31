package dev.hayohtee.quicknote.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.hayohtee.quicknote.data.local.model.LocalNote
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalNoteDao {
    @Query("SELECT * FROM note_table")
    fun getAll(): Flow<List<LocalNote>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getById(id: Long): Flow<LocalNote?>

    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: LocalNote)

    @Update
    suspend fun update(note: LocalNote)

    @Query("SELECT * FROM note_table WHERE title LIKE '%' || :query || '%'")
    fun searchNotes(query: String): Flow<List<LocalNote>>
}