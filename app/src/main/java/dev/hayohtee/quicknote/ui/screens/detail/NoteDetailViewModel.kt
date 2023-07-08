package dev.hayohtee.quicknote.ui.screens.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hayohtee.quicknote.data.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
): ViewModel() {
    private val _state = mutableStateOf(
        NoteDetailUIState(
            note = null,
            isLoading = true,
            errorMessage = null
        )
    )

    val state: State<NoteDetailUIState> = _state

    init {
        val id = stateHandle.get<Long>("note_id") ?: 0L
        viewModelScope.launch {
            noteRepository.getNote(id).collect { note ->
                _state.value = _state.value.copy(
                    note = note,
                    isLoading = false
                )
            }
        }
    }
}