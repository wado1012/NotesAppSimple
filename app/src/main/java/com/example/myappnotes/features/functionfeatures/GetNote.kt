package com.example.myappnotes.features.functionfeatures

import com.example.myappnotes.database.NoteRepository
import com.example.myappnotes.model.Note

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}