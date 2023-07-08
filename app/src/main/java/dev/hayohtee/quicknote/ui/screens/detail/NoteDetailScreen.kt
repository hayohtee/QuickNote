package dev.hayohtee.quicknote.ui.screens.detail

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.quicknote.R
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    state: NoteDetailUIState,
    onEditNote: (Long) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    if (state.note != null) {
                        IconButton(onClick = { onEditNote(state.note.id) }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = stringResource(id = R.string.edit_note)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (!state.isLoading && state.note != null) {
                Column(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = state.note.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = DateFormat.getPatternInstance(DateFormat.YEAR_MONTH_DAY)
                            .format(state.note.date),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = state.note.content,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun NoteDetailScreenPreview() {
    QuickNoteTheme {
        NoteDetailScreen(
            state = NoteDetailUIState(
                note = Note(
                    id = 2,
                    title = "Beautiful weather app UI concepts we wish existed",
                    content = "Beautiful weather app UI concepts we wish existed " +
                            "Beautiful weather app UI concepts we wish existed" +
                            "Beautiful weather app UI concepts we wish existed\n\n" +
                            "Beautiful weather app UI concepts we wish existed " +
                            "Beautiful weather app UI concepts we wish existed " +
                            "Beautiful weather app UI concepts we wish existed" +
                            "Beautiful weather app UI concepts we wish existed\n\n" +
                            "Beautiful weather app UI concepts we wish existed" +
                            "Beautiful weather app UI concepts we wish existed",
                    date = Date()
                ),
                isLoading = false,
                errorMessage = null
            ),
            onEditNote = {},
            onBackPressed = {}
        )
    }
}