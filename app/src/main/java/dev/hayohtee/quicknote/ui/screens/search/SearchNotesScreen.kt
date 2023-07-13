package dev.hayohtee.quicknote.ui.screens.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.hayohtee.quicknote.R
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.screens.notes.NoteList
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@Composable
fun SearchNoteScreen(
    notes: List<Note>,
    onNoteItemClick: (Long) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean, onActiveChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit
) {
    SearchNotesBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        notes = notes,
        onNoteItemClick = onNoteItemClick,
        onNavigateBack = onNavigateBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNotesBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean, onActiveChange: (Boolean) -> Unit,
    notes: List<Note>,
    onNoteItemClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        modifier = modifier,
        placeholder = {
            Text(text = stringResource(id = R.string.search_your_notes))
        },
        leadingIcon = {
            if (active) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_back)
                    )
                }
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        )
    ) {
        NoteList(notes = notes, onItemClick = onNoteItemClick)
    }
}

@Preview(showBackground = true)
@Composable
fun SearchNotesBarPreview() {
    QuickNoteTheme {
        SearchNotesBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = true,
            onActiveChange = {},
            notes = listOf(
                Note(
                    id = 1,
                    title = "How to make your personal brand stand out online",
                    content = "content",
                    date = Date()
                ),
                Note(
                    id = 2,
                    title = "10 excellent font pairing tool for designers",
                    content = "content",
                    date = Date()
                ),
                Note(
                    id = 3,
                    title = "10 excellent font pairing tool for designers",
                    content = "content",
                    date = Date()
                ),
                Note(
                    id = 4,
                    title = "Beautiful weather app UI concepts we wish existed",
                    content = "content",
                    date = Date()
                ),
                Note(
                    id = 5,
                    title = "Spotify's Reema Bhaghat on product design, music," +
                            " and the key to a happy career",
                    content = "content",
                    date = Date()
                ),
            ),
            onNoteItemClick = {},
            onNavigateBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchNotesScreenPreview() {
    QuickNoteTheme {
        SearchNoteScreen(
            notes = listOf(),
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            onNavigateBack = {},
            onNoteItemClick = {}
        )
    }
}