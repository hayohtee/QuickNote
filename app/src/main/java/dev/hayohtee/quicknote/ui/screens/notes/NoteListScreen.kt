package dev.hayohtee.quicknote.ui.screens.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.quicknote.R
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.components.NoteItem
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    state: NotesUIState,
    onSearchClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    onAddNoteClick: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onClick = onSearchClick,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            NoteFloatingActionButton(onClick = onAddNoteClick)
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (!state.isLoading && state.notes.isEmpty()) {
                Text(text = stringResource(id = R.string.empty_notes_label))
            }
            NoteList(
                notes = state.notes,
                onItemClick = onItemClick,
                Modifier.matchParentSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.notes))
        },
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_notes)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteList(notes: List<Note>, onItemClick: (Long) -> Unit, modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = modifier) {
        items(items = notes, key = { it.id }) { note ->
            NoteItem(
                note = note,
                onClick = onItemClick,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun NoteFloatingActionButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_note)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteFloatingActionButtonPreview() {
    QuickNoteTheme {
        NoteFloatingActionButton(onClick = { })
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListScreenPreview() {
    QuickNoteTheme {
        val notes = listOf(
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
                id = 2,
                title = "10 excellent font pairing tool for designers",
                content = "content",
                date = Date()
            ),
            Note(
                id = 2,
                title = "Beautiful weather app UI concepts we wish existed",
                content = "content",
                date = Date()
            ),
            Note(
                id = 3,
                title = "Spotify's Reema Bhaghat on product design, music," +
                        " and the key to a happy career",
                content = "content",
                date = Date()
            ),
        )

        NoteListScreen(
            state = NotesUIState(
                notes = notes,
                isLoading = false,
                errorMessage = null
            ),
            onSearchClick = {},
            onItemClick = {},
            onAddNoteClick = {}
        )
    }
}
