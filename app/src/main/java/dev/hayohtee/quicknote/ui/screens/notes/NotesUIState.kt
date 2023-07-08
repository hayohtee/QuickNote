package dev.hayohtee.quicknote.ui.screens.notes

import androidx.compose.runtime.Stable
import dev.hayohtee.quicknote.domain.Note

@Stable
data class NotesUIState(
    val notes: List<Note>,
    val isLoading: Boolean,
    val errorMessage: String?
)
