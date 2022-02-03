package com.example.myappnotes.database

import com.example.myappnotes.database.db.NoteDao
import com.example.myappnotes.model.Note
import kotlinx.coroutines.flow.Flow

// Note Repository zur Erstelleung der Funktionen für die Datenbank

class NoteRepository(
    private val dao: NoteDao
) {
    fun getNotes(): Flow<List<Note>>{
        return dao.getNotes()
    }
    suspend fun getNoteById(id: Int): Note?{
        return dao.getNoteById(id)
    }
    suspend fun insertNote(note: Note){
        dao.insertNote(note)
    }
    suspend fun deleteNote(note: Note){
        dao.deleteNote(note)
    }
}