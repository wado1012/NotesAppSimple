package com.example.myappnotes.elements

// Die verschiedenen Screens die die App besitzt

sealed class Screen(val route: String) {

    object NoteHomeScreen: Screen("note_screen")
    object CreateNoteScreen: Screen("create_note_screen")
    object DetailNoteScreen: Screen("detail_note_screen")
}