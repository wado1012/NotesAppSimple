package com.example.myappnotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Etinity Klasse zur Repräsentation der DatenbankTabelle
// Note beinhaltet einen Titel, den Notizeninhalt, einen Timestamp an dem die Notiz
// erstellt worden ist und eine ID, welche der PrimaryKey der Datenbank ist und
// über den die Notiz klar erkennbar und identifizierbar ist.
// Exception auch hier deklariert

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
) {
    companion object {

    }
}

class InvalidNoteException(message: String): Exception(message)