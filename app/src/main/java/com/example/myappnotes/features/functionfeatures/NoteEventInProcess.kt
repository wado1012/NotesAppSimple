package com.example.myappnotes.features.functionfeatures

import androidx.compose.ui.focus.FocusState

sealed class NoteEventInProcess {
    data class EnteredTitle(val value: String) : NoteEventInProcess()
    data class ChangeTitleFocus(val focusState: FocusState): NoteEventInProcess()
    data class EnteredContent(val value: String) : NoteEventInProcess()
    data class ChangeContentFocus(val focusState: FocusState) : NoteEventInProcess()
    object DeleteNote: NoteEventInProcess()
    object SaveNote : NoteEventInProcess()
}