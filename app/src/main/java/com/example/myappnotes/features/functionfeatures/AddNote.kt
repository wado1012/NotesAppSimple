package com.example.myappnotes.features.functionfeatures

import com.example.myappnotes.database.NoteRepository
import com.example.myappnotes.model.InvalidNoteException
import com.example.myappnotes.model.Note

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}