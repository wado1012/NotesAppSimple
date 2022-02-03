package com.example.myappnotes.features.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappnotes.features.functionfeatures.NoteEventInProcess
import com.example.myappnotes.features.functionfeatures.NoteTextFieldState
import com.example.myappnotes.features.functionfeatures.NoteUseCases
import com.example.myappnotes.model.InvalidNoteException
import com.example.myappnotes.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    /*TODO*/
    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
        hint = "Enter title..."
    )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
        hint = "Enter content..."
    )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent



    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }
    fun onEvent(eventInProcess: NoteEventInProcess) {
        when (eventInProcess) {
            is NoteEventInProcess.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = eventInProcess.value
                )
            }
            is NoteEventInProcess.ChangeTitleFocus-> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !eventInProcess.focusState.isFocused
                            && noteTitle.value.text.isBlank()
                )
            }
            is NoteEventInProcess.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = eventInProcess.value
                )
            }
            is NoteEventInProcess.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !eventInProcess.focusState.isFocused
                            && noteContent.value.text.isBlank()
                )
            }
            is NoteEventInProcess.DeleteNote -> {
                viewModelScope.launch {
                    currentNoteId?.let {
                        noteUseCases.getNote(it)?.let {
                            noteUseCases.deleteNote(
                                note = it
                            )
                        }
                    }
                    _eventFlow.emit(UiEvent.DeleteNote)
                }
            }

            is NoteEventInProcess.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note."
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
        object DeleteNote: UiEvent()
    }
}