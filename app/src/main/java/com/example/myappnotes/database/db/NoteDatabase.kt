package com.example.myappnotes.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myappnotes.model.Note

// Datenbank der App auf Grundlage von Room
// Verweis auf Klasse mit Entity

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}