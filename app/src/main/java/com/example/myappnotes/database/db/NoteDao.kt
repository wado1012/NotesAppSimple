package com.example.myappnotes.database.db

import androidx.room.*
import com.example.myappnotes.model.Note
import kotlinx.coroutines.flow.Flow

// QuerryDaten mit SQL zugriff
// Enthält auch bereits vorimplementierte methoden, die kein extra SQL Statement benötigen
// Vgl Vorlesungs Skript 113

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Delete
    suspend fun deleteNote(note: Note)

    operator fun invoke() {
        TODO("Not yet implemented")
    }

}