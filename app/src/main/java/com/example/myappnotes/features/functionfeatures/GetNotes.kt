package com.example.myappnotes.features.functionfeatures

import com.example.myappnotes.database.NoteRepository
import com.example.myappnotes.elements.NoteOrder
import com.example.myappnotes.elements.OrderType
import com.example.myappnotes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes()
            .map { notes ->
                when (noteOrder.orderType) {
                    is OrderType.Ascending -> {
                        when (noteOrder) {
                            is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        }
                    }
                    is OrderType.Descending -> {
                        when (noteOrder) {
                            is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        }
                    }
                }
            }
    }
}