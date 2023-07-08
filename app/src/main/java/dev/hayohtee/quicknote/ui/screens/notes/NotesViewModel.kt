package dev.hayohtee.quicknote.ui.screens.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hayohtee.quicknote.data.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = mutableStateOf(
        NotesUIState(
            notes = emptyList(),
            isLoading = true,
            errorMessage = null
        )
    )

    val state: State<NotesUIState> = _state

    init {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect { notes ->
                _state.value = _state.value.copy(
                    notes = notes,
                    isLoading = false
                )
            }
        }
    }
}