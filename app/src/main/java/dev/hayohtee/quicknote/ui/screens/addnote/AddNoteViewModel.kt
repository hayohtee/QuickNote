package dev.hayohtee.quicknote.ui.screens.addnote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hayohtee.quicknote.data.repository.NoteRepository
import dev.hayohtee.quicknote.domain.Note
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _state = mutableStateOf(
        Note(
            id = 0L,
            title = "",
            content = "",
            date = Date(),
        )
    )

    val state: State<Note> = _state

    val onTitleChange = { newTitle: String ->
        _state.value = _state.value.copy(
            title = newTitle
        )
    }

    val onContentChange = { newContent: String ->
        _state.value = _state.value.copy(
            content = newContent
        )
    }

    fun addNote() {
        if (state.value.title.isNotEmpty() || state.value.content.isNotEmpty()) {
            viewModelScope.launch {
                repository.addNote(state.value)
            }
        }
    }
}