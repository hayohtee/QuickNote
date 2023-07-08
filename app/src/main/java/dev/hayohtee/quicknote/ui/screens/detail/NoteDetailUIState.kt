package dev.hayohtee.quicknote.ui.screens.detail

import androidx.compose.runtime.Stable
import dev.hayohtee.quicknote.domain.Note

@Stable
data class NoteDetailUIState(
    val note: Note?,
    val isLoading: Boolean,
    val errorMessage: String?
)
