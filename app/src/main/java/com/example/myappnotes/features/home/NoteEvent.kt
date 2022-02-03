package com.example.myappnotes.features.home

import com.example.myappnotes.elements.NoteOrder
import com.example.myappnotes.model.Note

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder): NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote: NoteEvent()
    object ToggleOrderSection: NoteEvent()
}