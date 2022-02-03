package com.example.myappnotes.elements

// In welcher Reihenfolge werden die Notizelemente auf dem HomeScreen gespeichert
// Zunächst nur eine Möglichkeit über den Erstellzeitpunkt
// Kann im Nachhinein noch ergänzt werden

sealed class NoteOrder(val orderType: OrderType){
    class Date(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder{
        return Date(orderType)
    }
}