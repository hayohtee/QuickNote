package dev.hayohtee.quicknote.ui.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hayohtee.quicknote.data.repository.NoteRepository
import dev.hayohtee.quicknote.domain.Note
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _query: MutableState<String> = mutableStateOf("")
    val query: State<String> = _query
    private val _active = mutableStateOf(true)
    val active: State<Boolean> = _active
    private var _filteredNotes = mutableStateOf<List<Note>>(emptyList())
    val filteredNote: State<List<Note>> = _filteredNotes

    val onQueryChange = { newQuery: String ->
        _query.value = newQuery
        filterNotes(query.value)
    }

    val onActiveChange = { newActive: Boolean ->
        _active.value = newActive
    }

    fun filterNotes(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                noteRepository.searchNotes(query).collect { notes ->
                    _filteredNotes.value = notes
                }
            }
        } else {
            _filteredNotes.value = emptyList()
        }
    }
}