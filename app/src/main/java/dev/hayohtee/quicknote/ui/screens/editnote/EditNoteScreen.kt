package dev.hayohtee.quicknote.ui.screens.editnote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.components.NoteFields
import dev.hayohtee.quicknote.ui.screens.addnote.AddNoteTopAppBar
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@Composable
fun EditNoteScreen(
    note: Note?,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    updateNote: () -> Unit
) {
    Scaffold(
        topBar = {
            AddNoteTopAppBar(
                onBackButtonClick = onBackButtonClick,
                onItemClick = updateNote
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            NoteFields(
                title = note?.title ?: "",
                onTitleChange = onTitleChange,
                content = note?.content ?: "",
                onContentChange = onContentChange,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditNoteScreenPreview() {
    QuickNoteTheme {
        EditNoteScreen(
            note = Note(
                id = 1,
                title = "How to make your personal brand stand out online",
                content = "content",
                date = Date()
            ),
            onTitleChange = {},
            onContentChange = {},
            onBackButtonClick = {},
            updateNote = {}
        )
    }
}
