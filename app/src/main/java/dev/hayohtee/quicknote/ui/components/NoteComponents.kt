package dev.hayohtee.quicknote.ui.components

import android.icu.text.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.quicknote.R
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.theme.DarkRed
import dev.hayohtee.quicknote.ui.theme.Orange
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@Composable
fun NoteItem(
    note: Note,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(
            role = Role.Button,
            onClick = { onClick(note.id) }
        ),
        colors = CardDefaults.cardColors(
            containerColor = DarkRed,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title.ifEmpty { note.content },
                style = MaterialTheme.typography.titleLarge,
                maxLines = 8,
                overflow = TextOverflow.Clip
            )

            Text(
                text = DateFormat.getPatternInstance(DateFormat.YEAR_MONTH_DAY).format(note.date),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun NoteItemSpanned(
    note: Note,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(
            role = Role.Button,
            onClick = { onClick(note.id) }
        ),
        colors = CardDefaults.cardColors(
            containerColor = Orange,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title.ifEmpty { note.content },
                modifier = Modifier.weight(0.7f),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 8,
                overflow = TextOverflow.Clip,
            )
            Text(
                text = DateFormat.getPatternInstance(DateFormat.YEAR_MONTH_DAY).format(note.date),
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(0.3f)
                    .align(Alignment.Bottom)
            )

        }
    }
}

@Composable
fun NoteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            keyboardOptions = keyboardOptions,
            modifier = modifier.fillMaxWidth()
        )

        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = textStyle.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.5f
                    )
                )
            )
        }
    }
}

@Composable
fun NoteFields(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = focusRequester) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NoteTextField(
            value = title,
            onValueChange = onTitleChange,
            placeholder = stringResource(id = R.string.title),
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        NoteTextField(
            value = content,
            onValueChange = onContentChange,
            placeholder = stringResource(id = R.string.note),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Default
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.weight(1f).focusRequester(focusRequester)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun NoteTextFieldPreview() {
    QuickNoteTheme {
        NoteTextField(
            value = "Title",
            onValueChange = {},
            placeholder = stringResource(id = R.string.title),
            keyboardOptions = KeyboardOptions.Default,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    QuickNoteTheme {
        NoteItem(
            note = Note(
                id = 1,
                title = "How to make your personal brand stand out online",
                content = "content",
                date = Date()
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteItemSpannedPreview() {
    QuickNoteTheme {
        NoteItemSpanned(
            note = Note(
                id = 1,
                title = "How to make your personal brand stand out online",
                content = "content",
                date = Date()
            ),
            onClick = {}
        )
    }
}
