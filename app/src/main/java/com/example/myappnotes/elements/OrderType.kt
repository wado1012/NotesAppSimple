package com.example.myappnotes.elements

// Welche Möglichkeiten gibt es in der die Notizen sortiert sind

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}