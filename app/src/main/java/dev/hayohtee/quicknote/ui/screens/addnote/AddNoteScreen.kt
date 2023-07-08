package dev.hayohtee.quicknote.ui.screens.addnote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.hayohtee.quicknote.R
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.components.NoteFields
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@Composable
fun AddNoteScreen(
    note: Note,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    saveNote: () -> Unit
) {
    Scaffold(
        topBar = {
            AddNoteTopAppBar(
                onBackButtonClick = onBackButtonClick,
                onItemClick = saveNote
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NoteFields(
                title = note.title,
                onTitleChange = onTitleChange,
                content = note.content,
                onContentChange = onContentChange
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteTopAppBar(
    onBackButtonClick: () -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigate_back)
                )
            }
        },
        actions = {
            IconButton(onClick = onItemClick) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = stringResource(id = R.string.save_note)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddNoteScreenPreview() {
    QuickNoteTheme {
        AddNoteScreen(
            note = Note(0, "", "", Date()),
            onTitleChange = {},
            onContentChange = {},
            onBackButtonClick = {},
            saveNote = {}
        )
    }
}