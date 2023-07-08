package dev.hayohtee.quicknote.ui.screens.editnote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hayohtee.quicknote.data.repository.NoteRepository
import dev.hayohtee.quicknote.domain.Note
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = mutableStateOf<Note?>(null)
    val state: State<Note?> = _state

    val onTitleChange = { newTitle: String ->
        _state.value = _state.value?.copy(
            title = newTitle,
            date = Date()
        )
    }

    val onContentChange = { newContent: String ->
        _state.value = _state.value?.copy(
            content = newContent,
            date = Date()
        )
    }

    init {
        val id = savedStateHandle.get<Long>("note_id") ?: 0L

        viewModelScope.launch {
            noteRepository.getNote(id).collect { note ->
                _state.value = note
            }
        }
    }

    fun updateNote() {
        viewModelScope.launch {
            state.value?.let {
                noteRepository.updateNote(it)
            }
        }
    }
}