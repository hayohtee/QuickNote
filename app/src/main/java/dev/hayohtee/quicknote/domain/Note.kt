package dev.hayohtee.quicknote.domain

import java.util.Date

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val date: Date
)
