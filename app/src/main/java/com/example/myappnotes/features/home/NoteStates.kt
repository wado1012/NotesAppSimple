package com.example.myappnotes.features.home

import com.example.myappnotes.elements.NoteOrder
import com.example.myappnotes.elements.OrderType
import com.example.myappnotes.model.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)