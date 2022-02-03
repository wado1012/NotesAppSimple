package com.example.myappnotes.features.functionfeatures

import com.example.myappnotes.database.NoteRepository
import com.example.myappnotes.model.Note

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}